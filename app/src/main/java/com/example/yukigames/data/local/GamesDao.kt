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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(game : Game)

    /*
    @Delete
    suspend fun deleteGame(game: Game)

    @Query("SELECT * FROM game")
    fun getSavedGames() : Flow<List<Game>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<Game>>

     */

}