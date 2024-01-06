package com.example.yukigames.presentation.games.gamelist_viewmodel

import com.example.yukigames.domain.model.Game

data class GamesState(

    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = "",
    val page : String = "1"

)