package com.example.yukigames.presentation.main_fragment.viewmodels.home_viewmodel

sealed class HomeEvent {
    data class Games(val page : String) : HomeEvent()

}