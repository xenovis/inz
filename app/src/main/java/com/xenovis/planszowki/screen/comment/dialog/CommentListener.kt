package com.xenovis.planszowki.screen.comment.dialog

import java.io.Serializable

/**
 * Created by maciek on 04/12/16.
 */
interface CommentListener : Serializable {
    fun sendComment(message: String)
}