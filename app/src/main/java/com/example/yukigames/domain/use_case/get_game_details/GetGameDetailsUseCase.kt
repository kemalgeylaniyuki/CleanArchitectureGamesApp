package com.example.yukigames.domain.use_case.get_game_details

import com.example.yukigames.data.remote.dto.toGameDetails
import com.example.yukigames.domain.model.GameDetails
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeGetGameDetails(id : Int) : Flow<Resource<GameDetails>> = flow {

        try {

            emit(Resource.Loading())

            val gameDetails = repository.getGameDetails(id)
            emit(Resource.Success(data = gameDetails.toGameDetails()))

        }

        catch (e : IOError){
            emit(Resource.Error(message = "No internet connection!"))
        }
        catch (e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }

    }

}