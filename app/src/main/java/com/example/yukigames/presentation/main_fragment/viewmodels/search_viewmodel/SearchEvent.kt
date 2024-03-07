package com.example.yukigames.presentation.main_fragment.viewmodels.search_viewmodel

sealed class SearchEvent {
    data class Search(val search : String) : SearchEvent()

}