package com.xenovis.planszowki.screen.main.fragment.ranking

import android.support.v7.widget.LinearLayoutManager
import com.xenovis.planszowki.R
import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseFragment
import com.xenovis.planszowki.screen.details.BoardgameDetailsActivity
import com.xenovis.planszowki.screen.main.fragment.ranking.games.RankingAdapter
import com.xenovis.planszowki.screen.main.fragment.ranking.games.RankingItemDecoration
import kotlinx.android.synthetic.main.fragment_ranking.*

/**
 * Created by maciek on 30/11/16.
 */
class RankingFragment : BaseFragment<RankingPresenter, RankingView>(), RankingView {


    override val layout: Int = R.layout.fragment_ranking
    override val presenter = RankingPresenter()

    override fun showRankingList(list: List<ListedBoardgame>, listener: BoardgamesListener) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = RankingAdapter(list, listener)
        recycler_view.addItemDecoration(RankingItemDecoration(context))
    }

    override fun setupToolbarLayout() {
        toolbar.title = getString(R.string.app_name)
    }

    override fun showDetailsActivity(name: String) {
        context.startActivity(BoardgameDetailsActivity.newStartIntent(context, name))
    }
}