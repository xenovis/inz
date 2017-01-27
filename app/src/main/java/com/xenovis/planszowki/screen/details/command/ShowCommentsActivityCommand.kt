package com.xenovis.planszowki.screen.details.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.BoardgameDetailsView

/**
 * Created by maciek on 04/12/16.
 */

class ShowCommentsActivityCommand(val name: String, val logoUrl: String?) :UICommand<BoardgameDetailsView>{

    override fun execute(view: BoardgameDetailsView) {
        view.showCommentsActivity(name, logoUrl)
    }
}