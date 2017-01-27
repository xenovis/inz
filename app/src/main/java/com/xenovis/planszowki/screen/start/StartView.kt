package com.xenovis.planszowki.screen.start

import com.xenovis.planszowki.mvp.BaseView


/**
 * Created by maciek on 30/11/16.
 */

interface StartView : BaseView {
    fun changeRegisterSectionVisibility(visible: Boolean)
    fun showMainActivity()
}