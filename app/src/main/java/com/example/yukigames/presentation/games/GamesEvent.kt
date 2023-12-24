package com.example.yukigames.presentation.games

sealed class GamesEvent {
    data class Games(val page : String) : GamesEvent()

}