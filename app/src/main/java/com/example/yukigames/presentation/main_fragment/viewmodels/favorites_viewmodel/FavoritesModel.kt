package com.example.yukigames.presentation.main_fragment.viewmodels.favorites_viewmodel

import com.example.yukigames.domain.model.Game

data class FavoritesModel (

    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = ""
)