package com.xenovis.planszowki.screen.details.command

import com.xenovis.planszowki.data.api.response.BoardgameDetails
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.BoardgameDetailsView
import com.xenovis.planszowki.screen.details.listener.RatingListener

/**
 * Created by maciek on 04/12/16.
 */

class ShowRatingsDialogCommand(val game: BoardgameDetails, val ratingsListener: RatingListener) : UICommand<BoardgameDetailsView> {
    override fun execute(view: BoardgameDetailsView) {
        view.showRatingsDialog(game, ratingsListener)
    }

}