package com.xenovis.planszowki

import android.app.Application
import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.data.prefs.UserPreferences

/**
 * Created by maciek on 30/11/16.
 */
class PlanszowkiApp : Application(){

    companion object{
        lateinit var instance : PlanszowkiApp
    }

    override fun onCreate() {
        super.onCreate()
        UserPreferences(this)
        ApiManager()
        instance = this
    }
}