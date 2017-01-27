package com.xenovis.planszowki.data.api.response

/**
 * Created by maciek on 30/11/16.
 */
data class AuthResponse(val token_type: String, val access_token : String, val expires_in : Int, val refresh_token : String) {
}