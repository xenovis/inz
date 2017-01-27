package com.xenovis.planszowki.screen.details.dialog

import com.xenovis.planszowki.data.entity.Ratings
import com.xenovis.planszowki.mvp.BaseView
import com.xenovis.planszowki.widget.RatingLayout

/**
 * Created by maciek on 04/12/16.
 */
interface RatingView : BaseView{
    fun showUserRatings(ratings: Ratings)
    fun setupRatingListeners(complexityListener: RatingLayout.RatingListener, interactionListener: RatingLayout.RatingListener, randomnessListener: RatingLayout.RatingListener, awesomenessListener: RatingLayout.RatingListener)
}