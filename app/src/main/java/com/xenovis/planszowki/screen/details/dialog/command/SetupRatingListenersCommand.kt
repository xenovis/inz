package com.xenovis.planszowki.screen.details.dialog.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.details.dialog.RatingView
import com.xenovis.planszowki.widget.RatingLayout

/**
 * Created by maciek on 04/12/16.
 */

class SetupRatingListenersCommand(
        val complexityListener: RatingLayout.RatingListener,
        val interactionListener: RatingLayout.RatingListener,
        val randomnessListener: RatingLayout.RatingListener,
        val awesomenessListener: RatingLayout.RatingListener
) : UICommand<RatingView> {

    override fun execute(view: RatingView) {
        view.setupRatingListeners(
                complexityListener,
                interactionListener,
                randomnessListener,
                awesomenessListener
        )
    }

}
