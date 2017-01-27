package com.xenovis.planszowki.screen.main.activity.command

import android.os.Bundle
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.activity.MainView
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.BottomNavigationClickEvent
import rx.Subscriber

/**
 * Created by maciek on 03/12/16.
 */

class SetupBottomNavigationCommand(val savedInstanceState: Bundle?, val subscriber: Subscriber<BottomNavigationClickEvent>) :UICommand<MainView> {

    override fun execute(view: MainView) {
        view.setupBottomNavigation(savedInstanceState, subscriber)
    }
}