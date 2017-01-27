package com.xenovis.planszowki.data.api.manager

import com.xenovis.planszowki.data.api.interceptor.RequestInterceptor
import com.xenovis.planszowki.data.api.response.*
import com.xenovis.planszowki.data.prefs.UserPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class ApiManager {

    companion object{
        lateinit var instance : ApiManager
    }

    private val apiService : ApiService

    init {
//        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val okHttpClient = OkHttpClient.Builder().addInterceptor(RequestInterceptor(UserPreferences.instance)).build()
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://10.0.2.2:4000")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
        instance = this
    }

    fun login(username : String, password : String) : Observable<AuthResponse>{
        return apiService.login(username = username, password = password)
    }

    fun register(
            email : String,
            firstName : String,
            lastName : String,
            username : String,
            password : String
    ) : Observable<BooleanResponse> {
        return apiService.register(email, firstName, lastName, username, password)
    }

    fun getAllBoardGames() : Observable<List<ListedBoardgame>> {
        return apiService.getAllBoardgames()
    }

    fun getAllCategories() : Observable<List<Category>> {
        return apiService.getAllCategories()
    }

    fun searchBoardgames(query: String) : Observable<List<ListedBoardgame>> {
        return apiService.searchBoardgames(query)
    }

    fun getBoardgamesByCategory(category: String) : Observable<List<ListedBoardgame>> {
        return apiService.getBoardgamesByCategory(category)
    }

    fun getBoardgameDetails(name: String) : Observable<BoardgameDetails> {
        return apiService.getBoardgameDetails(name)
    }

    fun rateBoardgame(
            name: String,
            randomness: Int,
            complexity: Int,
            interaction: Int,
            awesomeness: Int
    ) :Observable<BoardgameDetails> {
        return apiService.rateBoardgame(
                name,
                randomness,
                complexity,
                interaction,
                awesomeness
        )
    }

    fun getCommentsForGame(name: String) : Observable<List<Comment>> {
        return apiService.getCommentsForGame(name)
    }

    fun sendComment(name: String, message: String) : Observable<List<Comment>> {
        return apiService.sendComment(name, message)
    }
}