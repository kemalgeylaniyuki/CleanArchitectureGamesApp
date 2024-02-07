package com.example.yukigames.presentation.main.viewmodels.search_viewmodel

sealed class SearchEvent {
    data class Search(val search : String) : SearchEvent()

}