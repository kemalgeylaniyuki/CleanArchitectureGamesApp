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

    private val _state = MutableStateFlow(CategoriesModel())
    val state : StateFlow<CategoriesModel> = _state

    private var job : Job? = null

    init {
        getGenres(_state.value.page)
    }

    private fun getGenres(page : String){

        job?.cancel()

        job = getGenresUseCase.executeGetGenresUseCase(page = page).onEach {

            when(it){

                is Resource.Success -> {
                    _state.value = CategoriesModel(genres = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = CategoriesModel(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = CategoriesModel(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}