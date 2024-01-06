package com.example.yukigames.domain.use_case.search_games

import com.example.yukigames.data.remote.dto.toGameList
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeSearchGames(search : String) : Flow<Resource<List<Game>>> = flow {

        try {

            emit(Resource.Loading())

            val gameList = repository.searchGames(search)
            if (gameList.count != 0){
                emit(Resource.Success(gameList.toGameList()))
            }

        }
        catch(e : IOError){
            emit(Resource.Error(message = "No Game Found!"))
        }
        catch(e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }

    }

}