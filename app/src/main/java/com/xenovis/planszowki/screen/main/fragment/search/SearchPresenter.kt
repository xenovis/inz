package com.xenovis.planszowki.screen.main.fragment.search

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.main.fragment.search.command.ShowDetailsActivityCommand
import com.xenovis.planszowki.screen.main.fragment.search.command.ShowSearchResultsCommand
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 01/12/16.
 */

class SearchPresenter : BasePresenter<SearchView>() {

    override fun onFirstViewAttachment() {

    }

    fun onSearchClicked(query: String) {
        ApiManager.instance
                .searchBoardgames(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ executeOnce(ShowSearchResultsCommand(it, getListener())) })
    }

    private fun getListener(): BoardgamesListener {
        return object : BoardgamesListener {
            override fun onBoardgameClicked(name: String) {
                executeOnce( ShowDetailsActivityCommand(name) )
            }
        }
    }

}