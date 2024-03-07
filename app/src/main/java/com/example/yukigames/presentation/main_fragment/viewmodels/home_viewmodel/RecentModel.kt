package com.example.yukigames.presentation.main_fragment.viewmodels.home_viewmodel

import com.example.yukigames.domain.model.Game

data class RecentModel(
    val isLoading : Boolean = false,
    //val recentGames : PagingData<Game> = PagingData.empty(),
    val recentGames : List<Game> = emptyList(),
    val error : String = "",
    val page : String = "1",
    val dates : String = "2020-02-01,2024-02-15",
    val ordering : String = "-released"
)