package com.example.yukigames.presentation.games.viewModels.search_viewmodel

sealed class SearchEvent {
    data class Search(val search : String) : SearchEvent()

}