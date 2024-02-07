package com.example.yukigames.domain.repository

import com.example.yukigames.data.remote.dto.GameDetailsDTO
import com.example.yukigames.data.remote.dto.GamesDTO
import com.example.yukigames.data.remote.dto.GenresDTO
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun getPopularGames(page : String) : GamesDTO

    suspend fun getRecentGames(page : String, dates : String, ordering : String) : GamesDTO

    suspend fun searchGames(search : String) : GamesDTO

    suspend fun getGameDetails(id : Int) : GameDetailsDTO

    suspend fun getGamesByGenre(genre : String, page : String) : GamesDTO

    suspend fun getGenres(page : String) : GenresDTO

    suspend fun upsert(game : Game)

    suspend fun delete(gameId : Int)

    suspend fun getFavoriteGames() : List<Game>




}