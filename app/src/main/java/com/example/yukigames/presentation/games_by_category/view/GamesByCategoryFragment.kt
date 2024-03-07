package com.example.yukigames.presentation.games_by_category.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentGamesByCategoryBinding
import com.example.yukigames.presentation.adapters.GamesByCategoryAdapter
import com.example.yukigames.presentation.base_fragment.BaseFragment
import com.example.yukigames.presentation.games_by_category.viewmodel.GamesByCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesByCategoryFragment : BaseFragment<FragmentGamesByCategoryBinding, GamesByCategoryViewModel>() {


    private lateinit var gamesByGenreAdapter : GamesByCategoryAdapter

    override val viewModel : GamesByCategoryViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGamesByCategoryBinding {
        return FragmentGamesByCategoryBinding.inflate(inflater, container, false)
    }

    var genre : String = ""

    override fun specificFeature() {
        super.specificFeature()
        arguments?.let {
            genre = GamesByCategoryFragmentArgs.fromBundle(it).genre
        }

        viewModel.getGamesByGenre("1",genre)
    }

    override fun recyclerViewUpdates() {
        gamesByGenreAdapter = GamesByCategoryAdapter()
        binding.recyclerViewGameByGenre.adapter = gamesByGenreAdapter
    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->

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