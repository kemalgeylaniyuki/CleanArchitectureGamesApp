package com.example.yukigames.domain.repository

import com.example.yukigames.data.remote.dto.GamesDTO

interface GameRepository {

    suspend fun getGames(page : String) : GamesDTO

    suspend fun searchGames(search : String) : GamesDTO

}