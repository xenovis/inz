package com.xenovis.planszowki.screen.details.command

import com.xenovis.planszowki.data.prefs.UserPreferences
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.BoardgameDetailsView
import com.xenovis.planszowki.screen.details.listener.ClickListener

/**
 * Created by maciek on 04/12/16.
 */

class SetupListenersIfNeededCommand(val userPreferences: UserPreferences, val listener: ClickListener) : UICommand<BoardgameDetailsView>{

    override fun execute(view: BoardgameDetailsView) {
        if (userPreferences.getAccessToken() != "") view.setupRatingListener(listener)
        view.setupCommentsListener(listener)
    }
}