package com.example.yukigames.presentation.games.viewModels.search_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.search_games.SearchGamesUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state : StateFlow<SearchState> = _state

    private var job : Job? = null


    init {
        getSearchedGames(_state.value.search)
    }



    private fun getSearchedGames(search : String){

        job?.cancel()

        job = searchGamesUseCase.executeSearchGames(search).onEach {

            when(it){

                is Resource.Success -> {
                    _state.value = SearchState(searched_games = it.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = SearchState(error = it.message ?: "Error!")
                }

            }

        }.launchIn(viewModelScope)

    }

    fun onEvent(event : SearchEvent){
        when(event){
            is SearchEvent.Search -> {
                getSearchedGames(event.search)
            }
        }
    }

}