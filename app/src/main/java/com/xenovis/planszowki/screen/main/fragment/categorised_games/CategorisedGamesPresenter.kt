package com.xenovis.planszowki.screen.main.fragment.categorised_games

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.main.fragment.categorised_games.command.ShowCategorisedGamesCommand
import com.xenovis.planszowki.screen.main.fragment.categorised_games.command.ShowDetailsActivityCommand
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 03/12/16.
 */

class CategorisedGamesPresenter : BasePresenter<CategorisedGamesView>() {
    var title: String? = null

    override fun onFirstViewAttachment() {
        println(title)
        ApiManager.instance
                .getBoardgamesByCategory(title!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { executeOnce( ShowCategorisedGamesCommand(it, getListener())) },
                        {error -> error.printStackTrace()})
    }

    private fun getListener(): BoardgamesListener {
        return object : BoardgamesListener {
            override fun onBoardgameClicked(name: String) {
                executeOnce( ShowDetailsActivityCommand(name) )
            }
        }
    }

    fun onCreate(title: String?) {
        this.title = title
    }
}
