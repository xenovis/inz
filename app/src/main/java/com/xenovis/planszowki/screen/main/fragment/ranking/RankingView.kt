package com.xenovis.planszowki.screen.main.fragment.ranking

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseView

/**
 * Created by maciek on 30/11/16.
 */

interface RankingView : BaseView {
    fun showRankingList(list: List<ListedBoardgame>, listener: BoardgamesListener)
    fun showDetailsActivity(name: String)
}