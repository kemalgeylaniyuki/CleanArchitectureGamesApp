package com.example.yukigames.presentation.games.viewModels.gamelist_viewmodel

sealed class GamesEvent {
    data class Games(val page : String) : GamesEvent()

}