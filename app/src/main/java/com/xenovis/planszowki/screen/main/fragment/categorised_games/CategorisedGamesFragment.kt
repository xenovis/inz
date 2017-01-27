package com.xenovis.planszowki.screen.main.fragment.categorised_games

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.xenovis.planszowki.R
import com.xenovis.planszowki.common.BoardgamesAdapter
import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.common.ListingItemDecoration
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseFragment
import com.xenovis.planszowki.screen.details.BoardgameDetailsActivity
import kotlinx.android.synthetic.main.fragment_ranking.*

/**
 * Created by maciek on 03/12/16.
 */

class CategorisedGamesFragment : BaseFragment<CategorisedGamesPresenter, CategorisedGamesView>(), CategorisedGamesView {



    companion object {
        private val CATEGORY_KEY = "CATEGORY"

        fun newInstance(category: String): CategorisedGamesFragment {
            var fragment = CategorisedGamesFragment()
            val args = Bundle()
            args.putString(CATEGORY_KEY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override val presenter = CategorisedGamesPresenter()
    override val layout = R.layout.fragment_ranking

    private var title : String? = null

    override fun setupToolbarLayout() {
        toolbar.title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments.getString(CATEGORY_KEY)
        presenter.onCreate(title)
    }

    override fun showCategorisedGames(list: List<ListedBoardgame>, listener: BoardgamesListener) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = BoardgamesAdapter(list, listener)
        recycler_view.addItemDecoration(ListingItemDecoration(context))
    }

    override fun showDetailsActivity(name: String) {
        context.startActivity(BoardgameDetailsActivity.newStartIntent(context, name))
    }
}