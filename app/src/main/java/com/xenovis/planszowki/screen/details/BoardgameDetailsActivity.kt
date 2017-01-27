package com.xenovis.planszowki.screen.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.xenovis.planszowki.BuildConfig
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.BoardgameDetails
import com.xenovis.planszowki.mvp.BaseActivity
import com.xenovis.planszowki.screen.comment.CommentsActivity
import com.xenovis.planszowki.screen.details.dialog.RatingDialog
import com.xenovis.planszowki.screen.details.listener.ClickListener
import com.xenovis.planszowki.screen.details.listener.RatingListener
import kotlinx.android.synthetic.main.activity_boardgame_details.*

class BoardgameDetailsActivity : BaseActivity<BoardgameDetailsPresenter, BoardgameDetailsView>(), BoardgameDetailsView {

    override val presenter = BoardgameDetailsPresenter()
    override val layout = R.layout.activity_boardgame_details

    companion object {
        private val BOARDGAME_KEY = "BOARDGAME"

        fun newStartIntent(context: Context, name: String) : Intent {
            val intent = Intent(context, BoardgameDetailsActivity::class.java)
            intent.putExtra(BOARDGAME_KEY, name)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra(BOARDGAME_KEY)
        toolbar.title = title
        presenter.onCreate(title)
    }

    override fun showBoardgameDetails(details: BoardgameDetails) {
        Picasso.with(this).load(BuildConfig.BASE_API_URL + '/' + details.bigImageUrl).into(big_image_view)
        complexity_rating_textview.text = details.averageComplexityRating.toString()
        interaction_rating_textview.text = details.averageInteractionRating.toString()
        randomness_rating_textview.text = details.averageRandomRating.toString()
        awesomeness_rating_textview.text = details.averageAwesomenessRating.toString()
        production_year_textview.text = details.productionYear.toString()
        minimum_age_textview.text = getString(R.string.minimum_age_format, details.minimumAge)
        multiplayer_textview.text = getString(R.string.multiplayer_format, details.minimumPlayers, details.maximumPlayers)
        average_time_textview.text = getString(R.string.gametime_format, details.averageRequiredTimeInMinutes)
        author_textview.text = details.author
        developer_textview.text = details.developer
        description_textview.text = details.description
        category_textview.text = details.gameType
    }

    override fun setupCommentsListener(listener: ClickListener) {
        comment_layout.setOnClickListener { listener.commentClicked() }
    }

    override fun setupRatingListener(listener: ClickListener) {
        ratings_layout.setOnClickListener { listener.ratingsClicked() }
    }

    override fun showRatingsDialog(game: BoardgameDetails, ratingsListener: RatingListener) {
        val fm = supportFragmentManager
        val dialog = RatingDialog.newInstance(
                game.userComplexityRating,
                game.userRandomRating,
                game.userAwesomenessRating,
                game.userInteractionRating,
                ratingsListener
        )
        dialog.show(fm, "rating_dialog")
    }

    override fun showCommentsActivity(name: String, logoUrl: String?) {
        startActivity(CommentsActivity.newStartIntent(this, name, logoUrl))
    }
}
