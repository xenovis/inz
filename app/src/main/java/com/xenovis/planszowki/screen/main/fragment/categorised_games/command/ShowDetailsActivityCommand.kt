package com.xenovis.planszowki.screen.main.fragment.categorised_games.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.categorised_games.CategorisedGamesView

/**
 * Created by maciek on 03/12/16.
 */

class ShowDetailsActivityCommand(val name: String) : UICommand<CategorisedGamesView> {

    override fun execute(view: CategorisedGamesView) {
        view.showDetailsActivity(name)
    }

}