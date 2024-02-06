package com.example.yukigames.domain.use_case.get_games_by_genre

import com.example.yukigames.data.remote.dto.toGameList
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.presentation.activitiy.Games
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetGamesByGenreUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeGetGamesByGenreUseCase(genre: String, page : String) : Flow<Resource<List<Game>>> = flow {

        try {
            emit(Resource.Loading())
            val gameList = repository.getGamesByGenre(genre, page)
            if(gameList.count != 0){
                emit(Resource.Success(gameList.toGameList()))
            }
            else{
                emit(Resource.Error(message = "No Game Found!"))
            }
        }
        catch (e : IOError){
            emit(Resource.Error(message = "No Internet Connection!"))
        }
        catch (e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }

    }

}