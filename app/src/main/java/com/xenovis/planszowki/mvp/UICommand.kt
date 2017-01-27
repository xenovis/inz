package com.xenovis.planszowki.mvp

interface UICommand<T : BaseView> {
    fun execute(view: T)
}