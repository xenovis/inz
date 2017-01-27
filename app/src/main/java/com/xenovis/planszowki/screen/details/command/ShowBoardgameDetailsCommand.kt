package com.xenovis.planszowki.screen.details.command

import com.xenovis.planszowki.data.api.response.BoardgameDetails
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.BoardgameDetailsView

/**
 * Created by maciek on 04/12/16.
 */

class ShowBoardgameDetailsCommand(val details: BoardgameDetails) : UICommand<BoardgameDetailsView> {

    override fun execute(view: BoardgameDetailsView) {
        view.showBoardgameDetails(details)
    }
}