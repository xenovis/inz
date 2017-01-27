package com.xenovis.planszowki.mvp

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.xenovis.planszowki.R

abstract class BaseDialogFragment<P : BasePresenter<TView>, TView : BaseView> : DialogFragment() {

    protected abstract var presenter: P
    val view : TView = this as TView

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

    protected abstract val dialogLayout: Int

    protected abstract val dialogTitle: Int

    protected abstract val positiveButtonText: Int

    protected abstract val neutralButtonText: Int

    protected abstract val negativeButtonText: Int

    protected open val positiveButtonAction: DialogInterface.OnClickListener?
        get() = null

    protected open val neutralButtonAction: DialogInterface.OnClickListener?
        get() = null

    protected open val negativeButtonAction: DialogInterface.OnClickListener?
        get() = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.DialogTheme)
        builder.setView(dialogLayout)
        builder.setTitle(dialogTitle)
        if (positiveButtonText != 0) builder.setPositiveButton(positiveButtonText, positiveButtonAction)
        if (neutralButtonText != 0) builder.setNeutralButton(neutralButtonText, neutralButtonAction)
        if (negativeButtonText != 0) builder.setNegativeButton(negativeButtonText, negativeButtonAction)
        return builder.create()
    }
}
