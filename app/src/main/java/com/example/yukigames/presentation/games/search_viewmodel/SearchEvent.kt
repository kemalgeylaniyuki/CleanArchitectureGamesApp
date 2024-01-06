package com.example.yukigames.presentation.games.search_viewmodel

sealed class SearchEvent {
    data class Search(val search : String) : SearchEvent()

}