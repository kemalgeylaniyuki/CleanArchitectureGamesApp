package com.example.yukigames.presentation.main.viewmodels.favorite_viewmodel

import com.example.yukigames.domain.model.Game

data class FavoriteState (

    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = ""
)