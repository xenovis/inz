package com.xenovis.planszowki.utils

import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.xenovis.planszowki.PlanszowkiApp

/**
 * Created by maciek on 03/12/16.
 */
object KeyboardUtils {
    fun showKeyBoard(windowToken: IBinder?) {
        var inputMethodManager: InputMethodManager = PlanszowkiApp.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(windowToken, InputMethodManager.RESULT_SHOWN, 0)
    }

    fun showKeyBoard(view: View?) {
        var inputMethodManager: InputMethodManager = PlanszowkiApp.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyBoard(windowToken: IBinder?) {
        var inputMethodManager: InputMethodManager = PlanszowkiApp.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}