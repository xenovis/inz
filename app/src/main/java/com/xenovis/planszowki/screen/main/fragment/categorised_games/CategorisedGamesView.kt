package com.xenovis.planszowki.screen.main.fragment.categorised_games

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseView

/**
 * Created by maciek on 03/12/16.
 */

interface CategorisedGamesView : BaseView {
    fun showCategorisedGames(list: List<ListedBoardgame>, listener: BoardgamesListener)
    fun showDetailsActivity(name: String)
}