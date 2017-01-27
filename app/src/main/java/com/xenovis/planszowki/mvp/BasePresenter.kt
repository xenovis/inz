package com.xenovis.planszowki.mvp

import rx.Subscription
import java.util.*

/**
 * Created by maciek on 29/11/16.
 */

abstract class BasePresenter<T : BaseView>{

    protected var view : T? = null
    private var wasViewAttachedBefore = false
    private val commandQueue = LinkedList<UICommand<T>>()
    private val mSubscriptions = ArrayList<Subscription>()
    private val mLongSubscriptions = ArrayList<Subscription>()


    open fun attachView(view : T) {
        this.view = view
        executeCommandQueue()
        if (!wasViewAttachedBefore) {
            onFirstViewAttachment()
            wasViewAttachedBefore = true
        }
    }

    protected abstract fun onFirstViewAttachment()

    open fun detachView() {
        view = null
        mSubscriptions
                .filterNot { it.isUnsubscribed }
                .forEach { it.unsubscribe() }
        mSubscriptions.clear()
    }

    protected fun addSubscription(subscription: Subscription) {
        mSubscriptions.add(subscription)
    }

    protected fun addLongSubscription(subscription: Subscription) {
        mLongSubscriptions.add(subscription)
    }

    private fun execute(command: UICommand<T>) {
        commandQueue.add(command)
        executeCommandQueue()
    }

    protected fun executeOnce(command: UICommand<T>) {
        execute(command)
    }

    private fun executeCommandQueue() {
        while (view != null) {
            commandQueue.poll()?.execute(view!!) ?: return
        }
    }

    fun onDestroy() {
        for (i in commandQueue.indices) {
            commandQueue.remove()
        }
        wasViewAttachedBefore = false
        mLongSubscriptions
                .filterNot { it.isUnsubscribed }
                .forEach { it.unsubscribe() }
        mLongSubscriptions.clear()
    }
}