package com.xenovis.planszowki.screen.details.listener

import com.xenovis.planszowki.data.entity.Ratings
import java.io.Serializable

/**
 * Created by maciek on 04/12/16.
 */
interface RatingListener : Serializable{
    fun onRatingsSelected(ratings: Ratings)
}