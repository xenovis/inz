package com.xenovis.planszowki.screen.details.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.entity.Ratings
import com.xenovis.planszowki.mvp.BaseDialogFragment
import com.xenovis.planszowki.screen.details.listener.RatingListener
import com.xenovis.planszowki.widget.RatingLayout

/**
 * Created by maciek on 04/12/16.
 */

class RatingDialog : BaseDialogFragment<RatingPresenter, RatingView>(), RatingView{
    override var presenter = RatingPresenter()
    override val dialogLayout = R.layout.dialog_rating
    override val dialogTitle = R.string.rating
    override val positiveButtonText: Int = R.string.rate
    override val neutralButtonText: Int = 0
    override val negativeButtonText: Int = R.string.cancel

    override val positiveButtonAction: DialogInterface.OnClickListener? = DialogInterface.OnClickListener { p0, p1 -> presenter.onPositiveClicked()}

    var complexity_rating_layout : RatingLayout? = null
    var interaction_rating_layout : RatingLayout? = null
    var randomness_rating_layout : RatingLayout? = null
    var awesomeness_rating_layout : RatingLayout? = null

    companion object {
        private val COMPLEXITY_KEY = "COMPLEXITY"
        private val AWESOMENESS_KEY = "AWESOMENESS"
        private val INTERACTION_KEY = "INTERACTION"
        private val RANDOMNESS_KEY = "RANDOMNESS"
        private val LISTENER_KEY = "LISTENER"

        fun newInstance(
                complexity: Int,
                randomness: Int,
                awesomeness: Int,
                interaction: Int,
                listener: RatingListener
        ) : RatingDialog {
            var dialog = RatingDialog()
            val args = Bundle()
            args.putInt(COMPLEXITY_KEY, complexity)
            args.putInt(AWESOMENESS_KEY, awesomeness)
            args.putInt(INTERACTION_KEY, interaction)
            args.putInt(RANDOMNESS_KEY, randomness)
            args.putSerializable(LISTENER_KEY, listener)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.onCreate(
                arguments.getInt(COMPLEXITY_KEY),
                arguments.getInt(AWESOMENESS_KEY),
                arguments.getInt(INTERACTION_KEY),
                arguments.getInt(RANDOMNESS_KEY),
                arguments.getSerializable(LISTENER_KEY) as RatingListener
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        var alertDialog = dialog as AlertDialog
        alertDialog.show()
        complexity_rating_layout = alertDialog.findViewById(R.id.complexity_rating_layout) as RatingLayout
        interaction_rating_layout = alertDialog.findViewById(R.id.interaction_rating_layout) as RatingLayout
        randomness_rating_layout = alertDialog.findViewById(R.id.randomness_rating_layout) as RatingLayout
        awesomeness_rating_layout = alertDialog.findViewById(R.id.awesomeness_rating_layout) as RatingLayout
        presenter.onViewCreated()
        return dialog
    }

    override fun showUserRatings(ratings: Ratings) {
        complexity_rating_layout?.setUserRating(ratings.complexity)
        interaction_rating_layout?.setUserRating(ratings.interaction)
        randomness_rating_layout?.setUserRating(ratings.randomness)
        awesomeness_rating_layout?.setUserRating(ratings.awesomeness)
    }

    override fun setupRatingListeners(
            complexityListener: RatingLayout.RatingListener,
            interactionListener: RatingLayout.RatingListener,
            randomnessListener: RatingLayout.RatingListener,
            awesomenessListener: RatingLayout.RatingListener
    ) {
        complexity_rating_layout?.setRatingListener(complexityListener)
        interaction_rating_layout?.setRatingListener(interactionListener)
        randomness_rating_layout?.setRatingListener(randomnessListener)
        awesomeness_rating_layout?.setRatingListener(awesomenessListener)
    }


}