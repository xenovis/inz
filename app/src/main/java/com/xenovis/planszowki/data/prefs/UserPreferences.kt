package com.xenovis.planszowki.data.prefs

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by maciek on 30/11/16.
 */

class UserPreferences(context: Context) {

    companion object {
        lateinit var instance : UserPreferences
    }

    init {
        instance = this
    }

    private var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getRefreshToken(): String {
        return sharedPreferences.getString(PREFS_REFRESH_TOKEN, "")
    }

    fun setRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString(PREFS_REFRESH_TOKEN, refreshToken).apply()
    }

    fun setAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(PREFS_ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(PREFS_ACCESS_TOKEN, "")
    }

    val PREFS_REFRESH_TOKEN = "PREFS_REFRESH_TOKEN"
    val PREFS_ACCESS_TOKEN = "PREFS_ACCESS_TOKEN"
}