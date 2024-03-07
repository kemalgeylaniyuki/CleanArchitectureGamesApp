package com.example.yukigames.presentation.main_fragment.viewmodels.categories_viewmodel

import com.example.yukigames.domain.model.Genres

data class CategoriesModel (

    val isLoading : Boolean = false,
    val genres : List<Genres> = emptyList(),
    val error : String = "",
    val page : String = "1"

)