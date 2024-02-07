package com.example.yukigames.presentation.main.viewmodels.home_viewmodel

sealed class HomeEvent {
    data class Games(val page : String) : HomeEvent()

}