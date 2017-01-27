package com.xenovis.planszowki.screen.main.activity

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import com.xenovis.planszowki.R
import com.xenovis.planszowki.mvp.BaseActivity
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.BottomNavigationClickEvent
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.BottomNavigationConfiguration
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.FragNavController
import com.xenovis.planszowki.screen.main.fragment.categories.CategoriesFragment
import com.xenovis.planszowki.screen.main.fragment.ranking.RankingFragment
import com.xenovis.planszowki.screen.main.fragment.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import java.util.*

class MainActivity : BaseActivity<MainPresenter, MainView>(), MainView {

    lateinit var fragmentController : FragNavController

    override val layout = R.layout.activity_main
    override var presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentController.onSaveInstanceState(outState)
        BottomNavigationConfiguration.onSaveInstanceState(outState, bottom_navigation.currentItem)
    }

    override fun setupBottomNavigation(savedInstanceState: Bundle?, subscriber: Subscriber<BottomNavigationClickEvent>) {
        val fragments = ArrayList<Fragment>(3)
        fragments.add(RankingFragment())
        fragments.add(SearchFragment())
        fragments.add(CategoriesFragment())
        BottomNavigationConfiguration.set(bottom_navigation, savedInstanceState, subscriber)
        fragmentController = FragNavController(savedInstanceState, supportFragmentManager, R.id.container, fragments)
        if (savedInstanceState == null) {
            fragmentController.switchTab(FragNavController.TAB1)
        }
    }

    override fun changeTabToPosition(position: Int) {
        runOnUiThread { fragmentController.switchTab(position) }
    }

    override fun clearStackForCurrentTab() {
        Handler(mainLooper).postDelayed({ fragmentController.clearStack() }, 200)
    }

    override fun onBackPressed() {
        if (fragmentController.currentStack.size != 1) {
            fragmentController.pop()
        } else {
            super.onBackPressed()
        }
    }
}
