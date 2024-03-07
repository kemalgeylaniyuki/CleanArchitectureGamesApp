package com.example.yukigames.presentation.main_fragment.viewmodels.home_viewmodel

import com.example.yukigames.domain.model.Game

data class PopularModel(
    val isLoading : Boolean = false,
    val popularGames : List<Game> = emptyList(),
    val error : String = "",
    val page : String = "1"
)