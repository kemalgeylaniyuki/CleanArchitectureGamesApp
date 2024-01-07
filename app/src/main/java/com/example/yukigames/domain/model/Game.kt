package com.example.yukigames.domain.model

import com.example.yukigames.data.remote.dto.Genre
import com.example.yukigames.data.remote.dto.ParentPlatform

data class Game(

    val background_image: String?,
    val id: Int,
    val genres: List<Genre>,
    val name: String?,
    val rating: Double?,
    val parent_platforms: List<ParentPlatform>,
    val released: String?

    )