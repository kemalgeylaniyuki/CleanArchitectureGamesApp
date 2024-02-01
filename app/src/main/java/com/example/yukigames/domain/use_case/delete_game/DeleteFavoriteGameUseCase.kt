package com.example.yukigames.domain.use_case.delete_game

import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteGameUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeDeleteFavoriteGameUseCase(gameId : Int) : Flow<Resource<Unit>> = flow {

        try {

            emit(Resource.Loading())

            repository.delete(gameId)

            emit(Resource.Success(Unit))

        }
        catch (e : Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Exception!"))
        }

    }

}