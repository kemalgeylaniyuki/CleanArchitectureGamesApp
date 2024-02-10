package com.example.yukigames.data.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.yukigames.data.local.GameDatabase
import com.example.yukigames.data.remote.GamesAPI
import com.example.yukigames.data.remote.dto.GameDetailsDTO
import com.example.yukigames.data.remote.dto.GamesDTO
import com.example.yukigames.data.remote.dto.GenresDTO
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.presentation.paging.GamesPagingSource
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api : GamesAPI,
    private val database: GameDatabase

) : GameRepository {
    override suspend fun getPopularGames(page: String): GamesDTO {
        return api.getPopularGames(page = page)
    }


    /*
    override fun getRecentGamesPaging(dates: String, ordering: String): Pager<Int, Game> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Sayfa başına öğe sayısı
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GamesPagingSource(api, "1", dates, ordering) }
        )
    }
    */


    override suspend fun getRecentGames(page: String, dates: String, ordering: String) : GamesDTO {
        return api.getRecentGames(page = page, dates = dates, ordering = ordering)
    }



    override suspend fun searchGames(search: String): GamesDTO {
        return api.searchGames(search = search)
    }

    override suspend fun getGameDetails(id: Int): GameDetailsDTO {
        return api.getGameDetails(id = id)
    }

    override suspend fun getGamesByGenre(genre: String, page : String): GamesDTO {
        return api.getGamesByGenre(genre = genre, page = page)
    }

    override suspend fun getGenres(page: String): GenresDTO {
        return api.getGenres(page = page)
    }

    override suspend fun upsert(game: Game) {
        return database.gamesDao().upsert(game)
    }

    override suspend fun delete(gameId : Int) {
        return database.gamesDao().deleteGame(gameId)

    }

    override suspend fun getFavoriteGames(): List<Game> {
        return database.gamesDao().getFavoriteGames()
    }


}