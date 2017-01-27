package com.xenovis.planszowki.data.api.response

/**
 * Created by maciek on 03/12/16.
 */
data class BoardgameDetails(
        val name: String,
        val gameType: String,
        val productionYear: Int,
        val author: String,
        val developer: String,
        val description: String,
        val logoUrl: String,
        val bigImageUrl: String,
        val averageRequiredTimeInMinutes: Int,
        val minimumAge: Int,
        val averageRandomRating: Double,
        val averageComplexityRating: Double,
        val averageInteractionRating: Double,
        val averageAwesomenessRating: Double,
        val userRandomRating: Int,
        val userComplexityRating: Int,
        val userInteractionRating: Int,
        val userAwesomenessRating: Int,
        val minimumPlayers: Int,
        val maximumPlayers: Int
) {
}