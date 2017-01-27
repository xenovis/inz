package com.xenovis.planszowki.screen.main.fragment.search.command

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.search.SearchView

/**
 * Created by maciek on 03/12/16.
 */
class ShowSearchResultsCommand(val list: List<ListedBoardgame>, val listener: BoardgamesListener) : UICommand<SearchView> {

    override fun execute(view: SearchView) {
        view.showSearchResults(list, listener)
    }
}