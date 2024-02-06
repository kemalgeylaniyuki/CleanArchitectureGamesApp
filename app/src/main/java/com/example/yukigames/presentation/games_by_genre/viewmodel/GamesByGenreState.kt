package com.example.yukigames.presentation.games_by_genre.viewmodel

import com.example.yukigames.domain.model.Game

data class GamesByGenreState(
    val isLoading : Boolean = false,
    val games : List<Game> = emptyList(),
    val error : String = "",
    val genre : String = "shooter",
    val page : String = "1"
)