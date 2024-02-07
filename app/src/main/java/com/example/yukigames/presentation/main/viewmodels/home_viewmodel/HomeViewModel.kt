package com.example.yukigames.presentation.main.viewmodels.home_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_popular_game.GetPopularGameUseCase
import com.example.yukigames.domain.use_case.get_recent_game.GetRecentGamesUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGameUseCase: GetPopularGameUseCase,
    private val getRecentGamesUseCase: GetRecentGamesUseCase
) : ViewModel() {

    private val _state_popular = MutableStateFlow(PopularState())
    val statePopular : StateFlow<PopularState> = _state_popular

    private val _state_recent = MutableStateFlow(RecentState())
    val stateRecent : StateFlow<RecentState> = _state_recent

    private var job1 : Job? = null
    private var job2 : Job? = null

    init {
        getPopularGames(_state_popular.value.page)
        getRecentGames(_state_recent.value.page, _state_recent.value.dates, _state_recent.value.ordering)
    }

    private fun getPopularGames(page : String){

        job1?.cancel()

        job1 = getGameUseCase.executeGetPopularGames(page = page).onEach {

            when(it){

                is Resource.Success -> {
                    _state_popular.value = PopularState(popularGames = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state_popular.value = PopularState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state_popular.value = PopularState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getRecentGames(page : String, dates : String, ordering : String){

        job2?.cancel()

        job2 = getRecentGamesUseCase.executeGetRecentGamesUseCase(page,dates, ordering).onEach {

            when(it){

                is Resource.Success -> {
                    _state_recent.value = RecentState(recentGames = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state_recent.value = RecentState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state_recent.value = RecentState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }



    /*
    fun onEvent(event: GamesEvent){
        when(event){
            is GamesEvent.Games -> {
                getGames(event.page)
            }
        }
    }
    */

}