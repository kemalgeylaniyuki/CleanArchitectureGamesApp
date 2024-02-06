package com.example.yukigames.data.remote.dto

import com.example.yukigames.domain.model.Genres

data class GenresDTO(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultX>
)

fun GenresDTO.toGenreList() : List<Genres> {
    return results.map { result -> Genres(
        result.games,
        result.games_count,
        result.id,
        result.image_background,
        result.name,
        result.slug
    ) }
}