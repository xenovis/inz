package com.xenovis.planszowki.screen.main.activity.bottom_navigation

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.xenovis.planszowki.R
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

object BottomNavigationConfiguration {
    private val CURRENT_TAB_SELECTED = "CURRENT_TAB_SELECTED"

    operator fun set(bottomNavigation: AHBottomNavigation, savedInstanceState: Bundle?, subscriber: Subscriber<BottomNavigationClickEvent>)
            //, final Subscriber<BottomNavigationClickEvent> subscriber)
    {
        val context = bottomNavigation.context
        val item1 = AHBottomNavigationItem(context.getString(R.string.ranking), R.drawable.ic_trophy_32dp)
        val item2 = AHBottomNavigationItem(context.getString(R.string.search), R.drawable.ic_search_32dp)
        val item3 = AHBottomNavigationItem(context.getString(R.string.categories), R.drawable.ic_book_32dp)
        val items = ArrayList<AHBottomNavigationItem>()
        items.add(item1)
        items.add(item2)
        items.add(item3)

        bottomNavigation.addItems(items)
        bottomNavigation.setUseElevation(true)

        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
        bottomNavigation.isBehaviorTranslationEnabled = false
        bottomNavigation.accentColor = ContextCompat.getColor(context, android.R.color.white)
        bottomNavigation.inactiveColor = ContextCompat.getColor(context, R.color.bottomNavigationInactiveColor)


        bottomNavigation.isForceTint = true
        bottomNavigation.isColored = false
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#ff0000"))

        if (savedInstanceState == null) {
            bottomNavigation.currentItem = 0
        } else {
            bottomNavigation.currentItem = savedInstanceState.getInt(CURRENT_TAB_SELECTED)
        }

        Observable.create(Observable.OnSubscribe<BottomNavigationClickEvent>() {
            if (!it.isUnsubscribed()) {
                bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
                    it.onNext(BottomNavigationClickEvent(position, wasSelected))
                    true
                }
            }
        }).debounce(200, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).subscribe({ subscriber.onNext(it) });
    }

    fun onSaveInstanceState(outState: Bundle, currentTabSelected: Int) {
        outState.putInt(CURRENT_TAB_SELECTED, currentTabSelected)
    }
}
