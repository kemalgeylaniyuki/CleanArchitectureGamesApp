package com.example.yukigames.presentation.main_fragment.viewmodels.favorite_viewmodel

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

    private val _state = MutableStateFlow(FavoriteModel())
    val state : StateFlow<FavoriteModel> = _state

    private var job : Job? = null

    init {
        getFavoriteGames()
    }

    private fun getFavoriteGames(){

        job?.cancel()

        job = getGamesFromFavoritesUseCase.executeGetGamesFromFavoritesUseCase().onEach {
            when(it){

                is Resource.Success -> {
                    _state.value = FavoriteModel(games = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = FavoriteModel(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = FavoriteModel(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

}