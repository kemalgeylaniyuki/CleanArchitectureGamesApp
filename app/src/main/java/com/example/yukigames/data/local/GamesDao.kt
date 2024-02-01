package com.example.yukigames.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun upsert(game : Game)


    @Query("DELETE FROM game WHERE id = :gameId")
    suspend fun deleteGame(gameId : Int)

    @Query("SELECT * FROM game")
    fun getSavedGame() : List<Game>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    suspend fun getFavoriteGames(): List<Game>


}