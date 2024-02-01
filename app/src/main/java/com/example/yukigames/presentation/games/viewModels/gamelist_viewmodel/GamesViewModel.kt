package com.example.yukigames.presentation.games.viewModels.gamelist_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_games_from_service.GetGameUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase) : ViewModel() {

    private val _state = MutableStateFlow(GamesState())
    val state : StateFlow<GamesState> = _state

    private var job : Job? = null

    init {
        getGames(_state.value.page)
    }

    private fun getGames(page : String){

        job?.cancel()

        job = getGameUseCase.executeGetGames(page = page).onEach {

            when(it){

                is Resource.Success -> {
                    _state.value = GamesState(games = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = GamesState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = GamesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }



    /*
    fun onEvent(event: GamesEvent){
        when(event){
            is GamesEvent.Games -> {
                getGames(event.page)
            }
        }
    }
    */

}