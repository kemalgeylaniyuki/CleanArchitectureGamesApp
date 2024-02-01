package com.example.yukigames.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yukigames.data.remote.dto.Genre
import com.example.yukigames.data.remote.dto.ParentPlatform

@Entity("game")
data class Game(
    val background_image: String?,
    val id: Int,
    val genres: List<Genre>?,
    val name: String?,
    val rating: Double?,
    val parent_platforms: List<ParentPlatform>?,
    val released: String?,
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0,
    val isFavorite: Boolean = false

)