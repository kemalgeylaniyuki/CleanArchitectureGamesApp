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

    private val _stateGamesByCategory = MutableStateFlow(GamesByCategoryModel())
    val stateGamesByCategory : StateFlow<GamesByCategoryModel> = _stateGamesByCategory

    init {
        getGamesByGenre(_stateGamesByCategory.value.page, _stateGamesByCategory.value.genre)
    }
    fun getGamesByGenre(page : String, genre : String){

        getGamesByGenreUseCase.executeGetGamesByGenreUseCase(page = page, genre = genre).onEach {

            when(it){

                is Resource.Success -> {
                    _stateGamesByCategory.emit(GamesByCategoryModel(games = it.data ?: emptyList()))
                }

                is Resource.Error -> {
                    _stateGamesByCategory.emit(GamesByCategoryModel(error = it.message ?: "Error !"))
                }

                is Resource.Loading -> {
                    _stateGamesByCategory.emit(GamesByCategoryModel(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)

    }
}