package com.example.yukigames.presentation.main_fragment.viewmodels.favorite_viewmodel

import com.example.yukigames.domain.model.Game

data class FavoriteModel (

    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = ""
)