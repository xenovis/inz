package com.xenovis.planszowki.screen.comment.command

import com.xenovis.planszowki.data.api.response.Comment
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.comment.CommentsView

/**
 * Created by maciek on 04/12/16.
 */

class ShowCommentsCommand(val list:List<Comment>) : UICommand<CommentsView> {

    override fun execute(view: CommentsView) {
        view.showComments(list)
    }
}