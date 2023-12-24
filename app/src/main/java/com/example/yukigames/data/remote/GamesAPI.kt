package com.example.yukigames.data.remote

import com.example.yukigames.data.remote.dto.GamesDTO
import com.example.yukigames.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {

    @GET("games")
    suspend fun getGames(
        @Query("key") key : String = API_KEY,
        @Query("page") page : String
    ) : GamesDTO

}