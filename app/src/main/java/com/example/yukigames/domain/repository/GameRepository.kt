package com.example.yukigames.domain.repository

import com.example.yukigames.data.remote.dto.GameDetailsDTO
import com.example.yukigames.data.remote.dto.GamesDTO

interface GameRepository {

    suspend fun getGames(page : String) : GamesDTO

    suspend fun searchGames(search : String) : GamesDTO

    suspend fun getGameDetails(id : Int) : GameDetailsDTO

}