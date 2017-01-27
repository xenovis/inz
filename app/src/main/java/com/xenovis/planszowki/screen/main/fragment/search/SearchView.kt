package com.xenovis.planszowki.screen.main.fragment.search

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseView


/**
 * Created by maciek on 01/12/16.
 */
interface SearchView : BaseView {
    fun showSearchResults(list: List<ListedBoardgame>, listener: BoardgamesListener)
    fun showDetailsActivity(name: String)
}