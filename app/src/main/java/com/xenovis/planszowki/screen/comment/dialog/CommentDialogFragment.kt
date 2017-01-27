package com.xenovis.planszowki.screen.comment.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.EditText
import com.xenovis.planszowki.R
import com.xenovis.planszowki.mvp.BaseDialogFragment

/**
 * Created by maciek on 04/12/16.
 */

class CommentDialogFragment : BaseDialogFragment<CommentDialogPresenter, CommentDialogView>(), CommentDialogView{

    override var presenter = CommentDialogPresenter()

    override val dialogLayout: Int = R.layout.dialog_comment
    override val dialogTitle: Int = R.string.comment

    override val positiveButtonText: Int = R.string.send

    override val neutralButtonText: Int = 0
    override val negativeButtonText: Int = R.string.cancel

    private var comment_edittext : EditText? = null

    companion object {
        private val LISTENER_KEY = "LISTENER"

        fun newInstance(listener: CommentListener) : CommentDialogFragment {
            val dialog = CommentDialogFragment()
            val args = Bundle()
            args.putSerializable(LISTENER_KEY, listener)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(arguments.get(LISTENER_KEY) as CommentListener)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        var alertDialog = dialog as AlertDialog
        alertDialog.show()
        comment_edittext = alertDialog.findViewById(R.id.comment_edittext) as EditText
        return dialog
    }

    override val positiveButtonAction: DialogInterface.OnClickListener = DialogInterface.OnClickListener {
        p0, p1 -> presenter.onSendClicked(comment_edittext?.text.toString())
    }

}