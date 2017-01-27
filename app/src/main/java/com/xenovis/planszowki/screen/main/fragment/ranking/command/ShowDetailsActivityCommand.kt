package com.xenovis.planszowki.screen.main.fragment.ranking.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.ranking.RankingView

/**
 * Created by maciek on 03/12/16.
 */
class ShowDetailsActivityCommand(val name: String) : UICommand<RankingView> {
    override fun execute(view: RankingView) {
        view.showDetailsActivity(name)
    }
}