package com.example.yukigames.presentation.game_details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_game_details.GetGameDetailsUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase : GetGameDetailsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(GameDetailsState())
    val state : StateFlow<GameDetailsState> = _state

    private var job : Job? = null

    fun getGameDetails(id : Int){

        job = getGameDetailsUseCase.executeGetGameDetails(id = id).onEach {
            when(it){

                is Resource.Success -> {
                    _state.value = GameDetailsState(game = it.data)
                }

                is Resource.Loading -> {
                    _state.value = GameDetailsState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = GameDetailsState(error = it.message ?: "Error!")
                }

            }
        }.launchIn(viewModelScope)

    }

}