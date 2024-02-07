package com.example.yukigames.presentation.main.viewmodels.search_viewmodel

import com.example.yukigames.domain.model.Game

data class SearchState(

    val isLoading : Boolean = false,
    val searched_games : List<Game> = emptyList(),
    val error : String = "",
    val search : String = "fifa"

)