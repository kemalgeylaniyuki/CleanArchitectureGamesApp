package com.example.yukigames.domain.use_case.get_genres

import com.example.yukigames.data.remote.dto.toGameList
import com.example.yukigames.data.remote.dto.toGenreList
import com.example.yukigames.domain.model.Genres
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val repository: GameRepository) {

    fun executeGetGenresUseCase(page : String) : Flow<Resource<List<Genres>>> = flow {

        try {

            emit(Resource.Loading())
            val genreList = repository.getGenres(page)
            if (genreList.count != 0){
                emit(Resource.Success(genreList.toGenreList()))
            }
            else{
                emit(Resource.Error(message = "No Genre Found!"))
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