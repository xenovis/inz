package com.xenovis.planszowki.screen.main.activity

import android.os.Bundle
import com.xenovis.planszowki.mvp.BaseView
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.BottomNavigationClickEvent
import rx.Subscriber


/**
 * Created by maciek on 30/11/16.
 */
interface MainView : BaseView {
    fun setupBottomNavigation(savedInstanceState: Bundle?, subscriber: Subscriber<BottomNavigationClickEvent>)
    fun changeTabToPosition(position: Int)
    fun clearStackForCurrentTab()
}