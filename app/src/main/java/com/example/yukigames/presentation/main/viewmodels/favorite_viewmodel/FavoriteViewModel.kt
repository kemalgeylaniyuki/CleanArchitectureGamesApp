package com.example.yukigames.presentation.main.viewmodels.favorite_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_games_from_favorites.GetGamesFromFavoritesUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getGamesFromFavoritesUseCase: GetGamesFromFavoritesUseCase) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state : StateFlow<FavoriteState> = _state

    private var job : Job? = null

    init {
        getFavoriteGames()
    }

    private fun getFavoriteGames(){

        job?.cancel()

        job = getGamesFromFavoritesUseCase.executeGetGamesFromFavoritesUseCase().onEach {
            when(it){

                is Resource.Success -> {
                    _state.value = FavoriteState(games = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = FavoriteState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

}