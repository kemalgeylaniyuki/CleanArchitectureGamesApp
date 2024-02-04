package com.example.yukigames.presentation.games.viewModels.genres_viewmodel

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
class GenresViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase) : ViewModel() {

    private val _state = MutableStateFlow(GenresState())
    val state : StateFlow<GenresState> = _state

    private var job : Job? = null

    init {
        getGenres(_state.value.page)
    }

    private fun getGenres(page : String){

        job?.cancel()

        job = getGenresUseCase.executeGetGenresUseCase(page = page).onEach {

            when(it){

                is Resource.Success -> {
                    _state.value = GenresState(genres = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = GenresState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = GenresState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}