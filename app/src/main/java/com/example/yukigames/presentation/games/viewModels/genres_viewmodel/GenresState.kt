package com.example.yukigames.presentation.games.viewModels.genres_viewmodel

import com.example.yukigames.domain.model.Genres

data class GenresState (

    val isLoading : Boolean = false,
    val genres : List<Genres> = emptyList(),
    val error : String = "",
    val page : String = "1"

)