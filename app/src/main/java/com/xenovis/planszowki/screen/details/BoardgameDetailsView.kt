
package com.xenovis.planszowki.screen.details

import com.xenovis.planszowki.data.api.response.BoardgameDetails
import com.xenovis.planszowki.mvp.BaseView
import com.xenovis.planszowki.screen.details.listener.ClickListener
import com.xenovis.planszowki.screen.details.listener.RatingListener

/**
 * Created by maciek on 03/12/16.
 */

interface BoardgameDetailsView : BaseView {
    fun showBoardgameDetails(details: BoardgameDetails)
    fun setupCommentsListener(listener: ClickListener)
    fun setupRatingListener(listener: ClickListener)
    fun showRatingsDialog(game: BoardgameDetails, ratingsListener: RatingListener)
    fun showCommentsActivity(name: String, logoUrl: String?)
}