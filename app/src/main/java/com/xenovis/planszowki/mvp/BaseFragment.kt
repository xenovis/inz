package com.xenovis.planszowki.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by maciek on 03/12/16.
 */
abstract class BaseFragment<P : BasePresenter<TView>, TView : BaseView> : Fragment(){

    protected abstract val presenter : P
    protected abstract val layout : Int
    val view : TView = this as TView
    private var fragmentView : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(layout, container, false)
        }
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(view)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        fragmentView = null
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarLayout()
    }

    abstract fun setupToolbarLayout()
}