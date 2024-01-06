package com.example.yukigames.data.remote

import com.example.yukigames.data.remote.dto.GamesDTO
import com.example.yukigames.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {

    //https://api.rawg.io/api/games?key=11e5f6d43e034bf98630663142da5f7c

    @GET("games")
    suspend fun getGames(
        @Query("key") key : String = API_KEY,
        @Query("page") page : String
    ) : GamesDTO

    @GET("games")
    suspend fun searchGames(
        @Query("key") key : String = API_KEY,
        @Query("search") search : String
    ) : GamesDTO

}