package com.xenovis.planszowki.screen.comment

import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.comment.command.ShowCommentDialogCommand
import com.xenovis.planszowki.screen.comment.command.ShowCommentsCommand
import com.xenovis.planszowki.screen.comment.command.ShowLogoAndNameCommand
import com.xenovis.planszowki.screen.comment.dialog.CommentListener
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 04/12/16.
 */

class CommentsPresenter : BasePresenter<CommentsView>() {

    var name : String? = null
    var logoUrl : String? = null

    override fun onFirstViewAttachment() {
        executeOnce( ShowLogoAndNameCommand(logoUrl, name!!) )
        ApiManager.instance
                .getCommentsForGame(name!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { executeOnce(ShowCommentsCommand(it)) },
                        { it.printStackTrace() })
    }

    fun onCreate(name: String?, logo: String?) {
        this.name = name
        logoUrl = logo
    }

    fun onFabClicked() {
        executeOnce( ShowCommentDialogCommand(getCommentListener()) )
    }

    private fun getCommentListener(): CommentListener {
        return object : CommentListener {
            override fun sendComment(message: String) {
                ApiManager.instance
                        .sendComment(name!!, message)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( { executeOnce(ShowCommentsCommand(it)) },
                                {it.printStackTrace()})
            }
        }
    }
}