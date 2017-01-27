package com.xenovis.planszowki.screen.details.dialog

import com.xenovis.planszowki.data.entity.Ratings
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.details.dialog.command.SetupRatingListenersCommand
import com.xenovis.planszowki.screen.details.dialog.command.ShowUserRatingsCommand
import com.xenovis.planszowki.screen.details.listener.RatingListener
import com.xenovis.planszowki.widget.RatingLayout

/**
 * Created by maciek on 04/12/16.
 */
class RatingPresenter : BasePresenter<RatingView>(){
    var complexity : Int? = null
    var awesomeness : Int? = null
    var interaction : Int? = null
    var randomness : Int? = null
    var listener : RatingListener? = null

    override fun onFirstViewAttachment() {

    }

    private fun getComplexityListener() : RatingLayout.RatingListener {
        return object : RatingLayout.RatingListener {
            override fun onRatingSelected(rating: Int) {
                complexity = rating
            }

        }
    }

    private fun getInteractionListener() : RatingLayout.RatingListener {
        return object : RatingLayout.RatingListener {
            override fun onRatingSelected(rating: Int) {
                interaction = rating
            }

        }
    }

    private fun getRandomnessListener() : RatingLayout.RatingListener {
        return object : RatingLayout.RatingListener {
            override fun onRatingSelected(rating: Int) {
                randomness = rating
            }

        }
    }

    private fun getAwesomenessListener() : RatingLayout.RatingListener {
        return object : RatingLayout.RatingListener {
            override fun onRatingSelected(rating: Int) {
                awesomeness = rating
            }

        }
    }

    fun onPositiveClicked() {
        val ratings = Ratings(complexity!!,interaction!!,randomness!!,awesomeness!!)
        listener?.onRatingsSelected(ratings)
    }

    fun onCreate(complexity: Int, awesomeness: Int, interaction: Int, randomness: Int, listener: RatingListener) {
        this.complexity = complexity
        this.awesomeness = awesomeness
        this.interaction = interaction
        this.randomness = randomness
        this.listener = listener
    }

    fun onViewCreated() {
        val ratings = Ratings(complexity!!,interaction!!,randomness!!,awesomeness!!)
        println(ratings)
        executeOnce( ShowUserRatingsCommand(ratings) )
        executeOnce( SetupRatingListenersCommand(
                getComplexityListener(),
                getInteractionListener(),
                getRandomnessListener(),
                getAwesomenessListener()
        ))
    }
}