package com.example.yukigames.domain.use_case.get_games_from_favorites

import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGamesFromFavoritesUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeGetGamesFromFavoritesUseCase() : Flow<Resource<List<Game>>> = flow {

        try {

            emit(Resource.Loading())

            val favGames = repository.getFavoriteGames()
            emit(Resource.Success(favGames))

        }

        catch(e : Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Exception!"))
        }

    }

}