package com.example.yukigames.presentation.main_fragment.viewmodels.categories_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_genres.GetGenresUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase) : ViewModel() {

    private val _stateCategories = MutableStateFlow(CategoriesModel())
    val stateCategories : StateFlow<CategoriesModel> = _stateCategories


    init {
        getGenres(_stateCategories.value.page)
    }

    private fun getGenres(page : String){

        getGenresUseCase.executeGetGenresUseCase(page = page).onEach {

            when(it){

                is Resource.Success -> {
                    //_state.value = CategoriesModel(genres = it.data ?: emptyList())
                    _stateCategories.emit(CategoriesModel(genres = it.data ?: emptyList()))
                }

                is Resource.Error -> {
                    _stateCategories.emit(CategoriesModel(error = it.message ?: "Error !"))
                }

                is Resource.Loading -> {
                    _stateCategories.emit(CategoriesModel(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)

    }

}