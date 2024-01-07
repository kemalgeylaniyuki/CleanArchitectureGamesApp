package com.example.yukigames.data.repository

import com.example.yukigames.data.remote.GamesAPI
import com.example.yukigames.data.remote.dto.GameDetailsDTO
import com.example.yukigames.data.remote.dto.GamesDTO
import com.example.yukigames.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val api : GamesAPI) : GameRepository {
    override suspend fun getGames(page: String): GamesDTO {
        return api.getGames(page = page)
    }

    override suspend fun searchGames(search: String): GamesDTO {
        return api.searchGames(search = search)
    }

    override suspend fun getGameDetails(id: Int): GameDetailsDTO {
        return api.getGameDetails(id = id)
    }

}