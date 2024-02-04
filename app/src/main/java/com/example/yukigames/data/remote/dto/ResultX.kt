package com.example.yukigames.data.remote.dto

data class ResultX(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)