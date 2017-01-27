package com.xenovis.planszowki.screen.main.fragment.search

import android.support.v7.widget.LinearLayoutManager
import com.xenovis.planszowki.R
import com.xenovis.planszowki.common.BoardgamesAdapter
import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.common.ListingItemDecoration
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import com.xenovis.planszowki.mvp.BaseFragment
import com.xenovis.planszowki.screen.details.BoardgameDetailsActivity
import com.xenovis.planszowki.widget.SearchLayout
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by maciek on 01/12/16.
 */

class SearchFragment : BaseFragment<SearchPresenter, SearchView>(), SearchView{
    override val presenter = SearchPresenter()

    override val layout = R.layout.fragment_search
    override fun setupToolbarLayout() {
        layout_search.setOnSearchClickedListener(object : SearchLayout.SearchListener {
            override fun onSearchClicked(string: String) {
                presenter.onSearchClicked(string)
            }
        })
    }

    override fun showSearchResults(list: List<ListedBoardgame>, listener: BoardgamesListener) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = BoardgamesAdapter(list, listener)
        recycler_view.addItemDecoration(ListingItemDecoration(context))
    }

    override fun showDetailsActivity(name: String) {
        context.startActivity(BoardgameDetailsActivity.newStartIntent(context, name))
    }
}