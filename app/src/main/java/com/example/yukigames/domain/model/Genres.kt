package com.example.yukigames.domain.model

import com.example.yukigames.data.remote.dto.Game

data class Genres(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String
)