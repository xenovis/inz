package com.xenovis.planszowki.screen.comment.dialog

import com.xenovis.planszowki.mvp.BasePresenter

/**
 * Created by maciek on 04/12/16.
 */

class CommentDialogPresenter : BasePresenter<CommentDialogView>() {

    var listener : CommentListener? = null

    override fun onFirstViewAttachment() {

    }

    fun onCreate(commentListener: CommentListener) {
        listener = commentListener
    }

    fun onSendClicked(comment: String) {
        listener?.sendComment(comment)
    }
}