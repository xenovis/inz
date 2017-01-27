package com.xenovis.planszowki.screen.comment

import com.xenovis.planszowki.data.api.response.Comment
import com.xenovis.planszowki.mvp.BaseView
import com.xenovis.planszowki.screen.comment.dialog.CommentListener

/**
 * Created by maciek on 04/12/16.
 */

interface CommentsView : BaseView{
    fun showName(name: String)
    fun showLogo(logoUrl: String)
    fun showComments(list: List<Comment>)
    fun showCommentDialog(listener: CommentListener)
}