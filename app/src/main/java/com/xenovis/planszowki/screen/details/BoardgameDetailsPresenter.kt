package com.xenovis.planszowki.screen.details

import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.data.api.response.BoardgameDetails
import com.xenovis.planszowki.data.entity.Ratings
import com.xenovis.planszowki.data.prefs.UserPreferences
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.details.command.SetupListenersIfNeededCommand
import com.xenovis.planszowki.screen.details.command.ShowBoardgameDetailsCommand
import com.xenovis.planszowki.screen.details.command.ShowCommentsActivityCommand
import com.xenovis.planszowki.screen.details.command.ShowRatingsDialogCommand
import com.xenovis.planszowki.screen.details.listener.ClickListener
import com.xenovis.planszowki.screen.details.listener.RatingListener
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 03/12/16.
 */
class BoardgameDetailsPresenter : BasePresenter<BoardgameDetailsView>() {

    var title: String? = null
    var game: BoardgameDetails? = null

    override fun onFirstViewAttachment() {
        ApiManager.instance
                .getBoardgameDetails(title!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    game = it
                    executeOnce(ShowBoardgameDetailsCommand(it))
                    executeOnce(SetupListenersIfNeededCommand(UserPreferences.instance, getClickListener()))
                },
                        { it.printStackTrace() })
    }

    private fun getClickListener(): ClickListener {
        return object : ClickListener {
            override fun ratingsClicked() {
                executeOnce(ShowRatingsDialogCommand(game!!, getRatingsListener()))
            }

            override fun commentClicked() {
                executeOnce(ShowCommentsActivityCommand(game!!.name, game!!.logoUrl))
            }
        }
    }

    private fun getRatingsListener(): RatingListener {
        return object : RatingListener {
            override fun onRatingsSelected(ratings: Ratings) {
                ApiManager.instance
                        .rateBoardgame(
                                game!!.name,
                                ratings.randomness,
                                ratings.complexity,
                                ratings.interaction,
                                ratings.awesomeness
                        ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            game = it
                            executeOnce(ShowBoardgameDetailsCommand(it))
                        }, { it.printStackTrace() })
            }
        }
    }

    fun onCreate(title: String?) {
        this.title = title
    }
}