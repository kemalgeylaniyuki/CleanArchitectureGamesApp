package com.example.yukigames.presentation.main_fragment.viewmodels.favorites_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_games_from_favorites.GetGamesFromFavoritesUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getGamesFromFavoritesUseCase: GetGamesFromFavoritesUseCase) : ViewModel() {

    private val _stateFavorites = MutableStateFlow(FavoritesModel())
    val stateFavorites : StateFlow<FavoritesModel> = _stateFavorites


    init {
        getFavoriteGames()
    }

    private fun getFavoriteGames(){

        getGamesFromFavoritesUseCase.executeGetGamesFromFavoritesUseCase().onEach {
            when(it){

                is Resource.Success -> {
                    _stateFavorites.emit(FavoritesModel(games = it.data ?: emptyList()))
                }

                is Resource.Error -> {
                    _stateFavorites.emit(FavoritesModel(error = it.message ?: "Error !"))
                }

                is Resource.Loading -> {
                    _stateFavorites.emit(FavoritesModel(isLoading = true))
                }

            }
        }.launchIn(viewModelScope)

    }

}