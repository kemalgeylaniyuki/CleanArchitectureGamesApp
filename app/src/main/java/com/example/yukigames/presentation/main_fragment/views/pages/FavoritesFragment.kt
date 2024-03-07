package com.example.yukigames.presentation.main_fragment.views.pages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentFavoritesBinding
import com.example.yukigames.presentation.base_fragment.BaseFragment
import com.example.yukigames.presentation.adapters.FavoriteAdapter
import com.example.yukigames.presentation.main_fragment.viewmodels.favorite_viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoriteViewModel>() {

    private lateinit var favoriteAdapter: FavoriteAdapter

    override val viewModel : FavoriteViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }


    override fun recyclerViewUpdates() {
        favoriteAdapter = FavoriteAdapter()
        binding.recyclerViewFavorites.adapter = favoriteAdapter
    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect{ state ->

                binding.progressBarFavorite.isVisible = state.isLoading
                binding.errorViewFavorite.isVisible = state.error.isNotBlank()
                binding.errorViewFavorite.text = state.error

                favoriteAdapter.setList(state.games)

            }
        }

    }

}