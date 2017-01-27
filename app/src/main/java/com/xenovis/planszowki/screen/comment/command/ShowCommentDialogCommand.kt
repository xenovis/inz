package com.xenovis.planszowki.screen.comment.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.comment.CommentsView
import com.xenovis.planszowki.screen.comment.dialog.CommentListener

/**
 * Created by maciek on 04/12/16.
 */

class ShowCommentDialogCommand(val listener: CommentListener) : UICommand<CommentsView>{

    override fun execute(view: CommentsView) {
        view.showCommentDialog(listener)
    }
}