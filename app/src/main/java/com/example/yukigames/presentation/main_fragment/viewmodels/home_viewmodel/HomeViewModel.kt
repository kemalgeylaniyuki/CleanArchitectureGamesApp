package com.example.yukigames.presentation.main_fragment.viewmodels.home_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yukigames.domain.use_case.get_popular_game.GetPopularGameUseCase
import com.example.yukigames.domain.use_case.get_recent_game.GetRecentGamesUseCase
import com.example.yukigames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _statePopular = MutableStateFlow(PopularModel())
    val statePopular: StateFlow<PopularModel> = _statePopular

    private val _stateRecent = MutableStateFlow(RecentModel())
    val stateRecent: StateFlow<RecentModel> = _stateRecent

    init {
        getPopularGames(_statePopular.value.page)
        getRecentGames(_stateRecent.value.page, _stateRecent.value.dates, _stateRecent.value.ordering)
        //getRecentGamesPaging(_state_recent.value.dates, _state_recent.value.ordering)
    }

    private fun getPopularGames(page : String) {

        getGameUseCase.executeGetPopularGames(page = page).onEach {

            when (it) {

                is Resource.Success -> {
                    _statePopular.value = PopularModel(popularGames = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _statePopular.value = PopularModel(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _statePopular.value = PopularModel(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getRecentGames(page: String, dates: String, ordering: String) {

        getRecentGamesUseCase.executeGetRecentGamesUseCase(page, dates, ordering).onEach {

            when (it) {

                is Resource.Success -> {
                    _stateRecent.value = RecentModel(recentGames = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateRecent.value = RecentModel(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _stateRecent.value = RecentModel(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }

        /*
    fun getRecentGamesPaging(dates: String, ordering: String) {
        getRecentGamesUseCase.executeGetRecentGamesPagingUseCase(dates, ordering)
            .onEach { pagingData ->

                _state_recent.value = _state_recent.value.copy(
                    recentGames = pagingData.map { it },
                    isLoading = false,
                    error = ""
                )
            }.launchIn(viewModelScope)
    }
    */

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



