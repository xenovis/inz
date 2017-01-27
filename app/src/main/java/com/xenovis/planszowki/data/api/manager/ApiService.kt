package com.xenovis.planszowki.data.api.manager

import com.xenovis.planszowki.data.api.response.*
import retrofit2.http.*
import rx.Observable

/**
 * Created by maciek on 30/11/16.
 */
interface ApiService {
    @FormUrlEncoded
    @POST("/oauth/token")
    fun login(
            @Field("client_id") clientId : String = "android",
            @Field("client_secret") clientSecret : String = "android",
            @Field("grant_type") grantType : String = "password",
            @Field("username") username : String,
            @Field("password") password : String
    ) : Observable<AuthResponse>

    @FormUrlEncoded
    @POST("/user/register")
    fun register(
            @Field("email") email : String,
            @Field("firstname") firstName : String,
            @Field("lastname") lastName : String,
            @Field("username") username : String,
            @Field("password") password : String
    ) : Observable<BooleanResponse>

    @GET("/boardgames/game")
    fun getAllBoardgames() : Observable<List<ListedBoardgame>>

    @GET("/categories/all")
    fun getAllCategories() : Observable<List<Category>>

    @GET("/boardgames/game")
    fun searchBoardgames(@Query("q") query: String) : Observable<List<ListedBoardgame>>

    @GET("/boardgames/game")
    fun getBoardgamesByCategory(@Query("category") category: String) : Observable<List<ListedBoardgame>>

    @GET("/boardgames/game/{game}")
    fun getBoardgameDetails(@Path("game") name: String) : Observable<BoardgameDetails>

    @FormUrlEncoded
    @PUT("/rating/{game}")
    fun rateBoardgame(
            @Path("game") name: String,
            @Field("randomness") randomness: Int,
            @Field("complexity") complexity: Int,
            @Field("interaction") interaction: Int,
            @Field("awesomeness") awesomeness: Int
    ) : Observable<BoardgameDetails>

    @GET("/comment/{game}")
    fun getCommentsForGame(@Path("game") name: String) : Observable<List<Comment>>

    @FormUrlEncoded
    @POST("/comment/{game}")
    fun sendComment(@Path("game") name: String, @Field("message") message: String) : Observable<List<Comment>>
}