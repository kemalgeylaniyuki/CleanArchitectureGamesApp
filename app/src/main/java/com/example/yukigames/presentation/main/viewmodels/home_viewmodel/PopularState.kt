package com.example.yukigames.presentation.main.viewmodels.home_viewmodel

import com.example.yukigames.domain.model.Game

data class PopularState(
    val isLoading : Boolean = false,
    val popularGames : List<Game> = emptyList(),
    val error : String = "",
    val page : String = "1"
)