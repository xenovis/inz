package com.xenovis.planszowki.screen.main.fragment.ranking

import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.main.fragment.ranking.command.ShowDetailsActivityCommand
import com.xenovis.planszowki.screen.main.fragment.ranking.command.ShowRankingListCommand
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 30/11/16.
 */
class RankingPresenter : BasePresenter<RankingView>(){
    override fun onFirstViewAttachment() {
    }

    override fun attachView(view: RankingView) {
        super.attachView(view)
        ApiManager.instance
                .getAllBoardGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { executeOnce(ShowRankingListCommand(it, getListener())) },
                        {error -> println("error = [${error}]")})
    }

    private fun getListener(): BoardgamesListener {
        return object : BoardgamesListener {
            override fun onBoardgameClicked(name: String) {
                executeOnce( ShowDetailsActivityCommand(name) )
            }

        }
    }
}