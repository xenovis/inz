package com.xenovis.planszowki.screen.main.fragment.ranking.command

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.ranking.RankingView

/**
 * Created by maciek on 03/12/16.
 */
class ShowRankingListCommand(val list: List<ListedBoardgame>, val listener: BoardgamesListener) : UICommand<RankingView> {

    override fun execute(view: RankingView) {
        view.showRankingList(list, listener)
    }
}