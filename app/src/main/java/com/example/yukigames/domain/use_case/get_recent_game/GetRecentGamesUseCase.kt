package com.example.yukigames.domain.use_case.get_recent_game

import android.util.Log
import androidx.paging.PagingData
import com.example.yukigames.data.remote.dto.toGameList
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetRecentGamesUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeGetRecentGamesUseCase(page : String, dates : String, ordering : String) : Flow<Resource<List<Game>>> = flow {

        try {

            emit(Resource.Loading())
            val gameList = repository.getRecentGames(page,dates,ordering)
            if (gameList.count != 0){
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

    /*
    fun executeGetRecentGamesPagingUseCase(dates: String, ordering: String): Flow<PagingData<Game>> {
        return repository.getRecentGamesPaging(dates, ordering).flow
    }
    */
}
