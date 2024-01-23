package com.example.yukigames.presentation.game_details.viewModel

import com.example.yukigames.domain.model.GameDetails

data class GameDetailsState(

    val isLoading : Boolean = false,
    val gameDetails : GameDetails? = null,
    val error : String = "",

)