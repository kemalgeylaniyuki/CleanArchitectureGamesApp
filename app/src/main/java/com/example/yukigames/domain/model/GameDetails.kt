package com.example.yukigames.domain.model


import com.example.yukigames.data.remote.dto.Genre
import com.example.yukigames.data.remote.dto.GenreX
import com.example.yukigames.data.remote.dto.ParentPlatform

data class GameDetails(

    val background_image: String?,
    val description_raw: String,
    val genres: List<Genre>,
    val id: Int,
    val name_original: String?,
    val rating: Double?,
    val released: String?,
    val website: String?,
    val youtube_count: Int?,
    val parent_platforms: List<ParentPlatform>,
    val added: Int,
)
