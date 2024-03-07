package com.example.yukigames.presentation.games_by_category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_games_by_genre.GetGamesByGenreUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesByCategoryViewModel @Inject constructor(
    private val getGamesByGenreUseCase: GetGamesByGenreUseCase) : ViewModel() {

    private val _state = MutableStateFlow(GamesByCategoryModel())
    val state : StateFlow<GamesByCategoryModel> = _state

    private var job : Job? = null
    init {
        getGamesByGenre(_state.value.page, _state.value.genre)
    }
    fun getGamesByGenre(page : String, genre : String){

        job?.cancel()

        job = getGamesByGenreUseCase.executeGetGamesByGenreUseCase(page = page, genre = genre).onEach {

            when(it){

                is Resource.Success -> {
                    _state.value = GamesByCategoryModel(games = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = GamesByCategoryModel(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = GamesByCategoryModel(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}