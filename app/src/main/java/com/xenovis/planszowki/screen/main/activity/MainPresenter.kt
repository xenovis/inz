package com.xenovis.planszowki.screen.main.activity

import android.os.Bundle
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.BottomNavigationClickEvent
import com.xenovis.planszowki.screen.main.activity.command.ChangeTabCommand
import com.xenovis.planszowki.screen.main.activity.command.SetupBottomNavigationCommand
import com.xenovis.planszowki.screen.main.activity.command.ShowInitialFragmentForCurrentTabIfNeededCommand
import rx.Subscriber

/**
 * Created by maciek on 30/11/16.
 */

class MainPresenter() : BasePresenter<MainView>() {

    var savedInstanceState: Bundle? = null

    override fun onFirstViewAttachment() {
        executeOnce(SetupBottomNavigationCommand(savedInstanceState, getSubscriber()))
    }

    private fun getSubscriber(): Subscriber<BottomNavigationClickEvent> {
        return object : Subscriber<BottomNavigationClickEvent>() {
            override fun onNext(event: BottomNavigationClickEvent) {
                executeOnce(ChangeTabCommand(event.position))
                executeOnce(ShowInitialFragmentForCurrentTabIfNeededCommand(event.shouldClearStack))
            }

            override fun onError(e: Throwable?) {
            }

            override fun onCompleted() {
            }
        }
    }

    fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }

}