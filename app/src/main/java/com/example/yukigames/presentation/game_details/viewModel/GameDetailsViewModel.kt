package com.example.yukigames.presentation.game_details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.model.GameDetails
import com.example.yukigames.domain.use_case.delete_game.DeleteFavoriteGameUseCase
import com.example.yukigames.domain.use_case.get_game_details.GetGameDetailsUseCase
import com.example.yukigames.domain.use_case.upsert_game.UpsertGameUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase : GetGameDetailsUseCase,
    private val upsertGameUseCase : UpsertGameUseCase,
    private val deleteFavoriteGameUseCase: DeleteFavoriteGameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameDetailsModel())
    val state : StateFlow<GameDetailsModel> = _state


    private var job : Job? = null


    fun getGameDetails(id : Int){

        job?.cancel()

        job = getGameDetailsUseCase.executeGetGameDetails(id = id).onEach {
            when(it){

                is Resource.Success -> {
                    _state.value = GameDetailsModel(gameDetails = it.data)
                }

                is Resource.Loading -> {
                    _state.value = GameDetailsModel(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = GameDetailsModel(error = it.message ?: "Error!")
                }

            }
        }.launchIn(viewModelScope)

    }


    fun toggleFavoriteStatus(gameDetails: GameDetails, isFavorite : Boolean) {
        viewModelScope.launch {
            // GameDetails'dan Game'e dönüşüm yaparak upsert işlemi gerçekleştir
                val game = Game(
                    background_image = gameDetails.background_image,
                    id = gameDetails.id,
                    genres = gameDetails.genres, // Eğer Game sınıfında bir genres özelliği varsa
                    name = gameDetails.name_original,
                    rating = gameDetails.rating,
                    parent_platforms = gameDetails.parent_platforms,
                    released = gameDetails.released,
                    isFavorite = true
                )

            if(isFavorite){
                upsertGameUseCase.executeUpsertGameUseCase(game).collect()
            }
            else{
                deleteFavoriteGameUseCase.executeDeleteFavoriteGameUseCase(game.id).collect()
            }
        }
    }

}