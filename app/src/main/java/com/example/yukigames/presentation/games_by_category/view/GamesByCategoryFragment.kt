package com.example.yukigames.presentation.games_by_category.view

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentGamesByCategoryBinding
import com.example.yukigames.presentation.adapters.GamesByCategoryAdapter
import com.example.yukigames.presentation.BaseFragment
import com.example.yukigames.presentation.games_by_category.viewmodel.GamesByCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesByCategoryFragment : BaseFragment<FragmentGamesByCategoryBinding, GamesByCategoryViewModel>() {


    private lateinit var gamesByGenreAdapter : GamesByCategoryAdapter

    var genre : String = ""

    override fun getViewModelClass(): Class<GamesByCategoryViewModel> = GamesByCategoryViewModel::class.java

    override fun getViewBinding(): FragmentGamesByCategoryBinding = FragmentGamesByCategoryBinding.inflate(layoutInflater)

    override fun setUpViews() {
        gamesByGenreAdapter = GamesByCategoryAdapter()
        binding.recyclerViewGameByGenre.adapter = gamesByGenreAdapter

        arguments?.let {
            genre = GamesByCategoryFragmentArgs.fromBundle(it).genre
        }

        viewModel.getGamesByGenre("1",genre)
    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateGamesByCategory.collect { state ->

                // Update UI based on the state
                binding.progressBarGameByGenre.isVisible = state.isLoading
                binding.errorViewGameByGenre.isVisible = state.error.isNotBlank()
                binding.errorViewGameByGenre.text = state.error

                //if(state.genre != genre){
                //viewModel.getGamesByGenre(state.page,genre)}

                // Update game list
                gamesByGenreAdapter.setGamesByCategoryList(state.games)
            }
        }

    }

}