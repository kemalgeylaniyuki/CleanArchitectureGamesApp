package com.example.yukigames.presentation.games.viewModels.favorite_games_viewmodel

import com.example.yukigames.domain.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoriteState (

    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = ""
)