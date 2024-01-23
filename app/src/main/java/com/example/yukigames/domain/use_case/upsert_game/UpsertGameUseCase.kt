package com.example.yukigames.domain.use_case.upsert_game

import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.model.GameDetails
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpsertGameUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeUpsertGameUseCase(game : Game) : Flow<Resource<Unit>> = flow {

        try {

            emit(Resource.Loading())

            repository.upsert(game)

            emit(Resource.Success(Unit))

        }
        catch(e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "Exception!"))
        }

    }

}