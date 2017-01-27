package com.xenovis.planszowki.screen.start

import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.data.api.response.AuthResponse
import com.xenovis.planszowki.data.prefs.UserPreferences
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.start.command.ChangeRegisterSectionVisibilityCommand
import com.xenovis.planszowki.screen.start.command.ShowMainActivityCommand
import rx.schedulers.Schedulers

/**
 * Created by maciek on 29/11/16.
 */

class StartPresenter : BasePresenter<StartView>(){

    private var registerSectionVisible = false

    override fun onFirstViewAttachment() {

    }



    fun loginClicked(username: String, password: String) {
        if (registerSectionVisible) {
            executeOnce( ChangeRegisterSectionVisibilityCommand(false) )
            registerSectionVisible = false
        } else {
            login(password, username)
        }
    }

    private fun login(password: String, username: String) {
        ApiManager
                .instance
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .subscribe { handleLogin(it) }
    }

    fun registerClicked(username: String, password: String, email: String, firstName: String, lastName: String) {
        if (!registerSectionVisible) {
            executeOnce( ChangeRegisterSectionVisibilityCommand(true) )
            registerSectionVisible = true
        } else {
            register(email, firstName, lastName, password, username)
        }
    }

    private fun register(email: String, firstName: String, lastName: String, password: String, username: String) {
        ApiManager
                .instance
                .register(email, firstName, lastName, username, password)
                .subscribeOn(Schedulers.io())
                .flatMap { ApiManager.instance.login(username, password) }
                .subscribe {
                    handleLogin(it)
                }
    }

    private fun handleLogin(it: AuthResponse) {
        UserPreferences.instance.setAccessToken(accessToken = it.access_token)
        UserPreferences.instance.setRefreshToken(refreshToken = it.refresh_token)
        showMainActivity()
    }

    private fun showMainActivity() {
        executeOnce( ShowMainActivityCommand() )
    }

    fun guestClicked() {
        UserPreferences.instance.setAccessToken(accessToken = "")
        UserPreferences.instance.setRefreshToken(refreshToken = "")
        showMainActivity()
    }
}