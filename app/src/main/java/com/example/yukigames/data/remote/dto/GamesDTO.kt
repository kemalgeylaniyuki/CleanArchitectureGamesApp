package com.example.yukigames.data.remote.dto

import com.example.yukigames.domain.model.Game

data class GamesDTO(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any,
    val results: List<Result>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)

fun GamesDTO.toGameList() : List<Game> {
    return results.map { result -> Game(

        result.background_image,
        result.id,
        result.genres,
        result.name,
        result.rating,
        result.parent_platforms,
        result.released

    ) }
}