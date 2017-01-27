package com.xenovis.planszowki.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by maciek on 29/11/16.
 */

abstract class BaseActivity<P : BasePresenter <TView>, TView : BaseView> : AppCompatActivity()   {
    val view = this as TView
    protected abstract val presenter : P
    protected abstract val layout : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(view)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}