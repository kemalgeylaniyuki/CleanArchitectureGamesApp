package com.example.yukigames.presentation.games_by_category.viewmodel

import com.example.yukigames.domain.model.Game

data class GamesByCategoryModel(
    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = "",
    val genre : String = "shooter",
    val page : String = "1"
)