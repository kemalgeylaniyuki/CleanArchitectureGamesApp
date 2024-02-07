package com.example.yukigames.presentation.main.viewmodels.home_viewmodel

import com.example.yukigames.domain.model.Game

data class RecentState(
    val isLoading : Boolean = false,
    val recentGames : List<Game> = emptyList(),
    val error : String = "",
    val page : String = "1",
    val dates : String = "2020-02-01,2024-03-01",
    val ordering : String = "-released"
)