package com.xenovis.planszowki.screen.details.dialog.command

import com.xenovis.planszowki.data.entity.Ratings
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.dialog.RatingView

/**
 * Created by maciek on 04/12/16.
 */
class ShowUserRatingsCommand(val ratings: Ratings) : UICommand<RatingView>{
    override fun execute(view: RatingView) {
        view.showUserRatings(ratings)
    }
}