package com.xenovis.planszowki.screen.main.fragment.categorised_games.command

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.categorised_games.CategorisedGamesView

/**
 * Created by maciek on 03/12/16.
 */

class ShowCategorisedGamesCommand(val list: List<ListedBoardgame>, val listener: BoardgamesListener) : UICommand<CategorisedGamesView>{

    override fun execute(view: CategorisedGamesView) {
        view.showCategorisedGames(list, listener)
    }
}