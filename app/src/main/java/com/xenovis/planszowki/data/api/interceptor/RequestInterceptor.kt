package com.xenovis.planszowki.data.api.interceptor

import com.xenovis.planszowki.data.prefs.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by maciek on 30/11/16.
 */
class RequestInterceptor(preferences: UserPreferences) : Interceptor {

    var preferences : UserPreferences = preferences

    override fun intercept(chain: Interceptor.Chain?): Response {
        var requestBuilder = chain!!.request().newBuilder().header("Accept", "application/json")
        if (preferences.getAccessToken() != "") {
            requestBuilder.addHeader("Authorization", "Bearer " + preferences.getAccessToken())
        }
        return chain.proceed(requestBuilder.build())
    }

}