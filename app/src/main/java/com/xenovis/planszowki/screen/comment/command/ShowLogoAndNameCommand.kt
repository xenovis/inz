package com.xenovis.planszowki.screen.comment.command

import com.xenovis.planszowki.BuildConfig
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.comment.CommentsView

/**
 * Created by maciek on 04/12/16.
 */

class ShowLogoAndNameCommand(val logoUrl: String?, val name: String) : UICommand<CommentsView>{

    override fun execute(view: CommentsView) {
        view.showName(name)
        view.showLogo(BuildConfig.BASE_API_URL + '/' + logoUrl)
    }
}