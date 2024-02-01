package com.example.yukigames.presentation.games.views.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yukigames.databinding.FragmentFavoritesBinding
import com.example.yukigames.presentation.adapters.FavoriteAdapter
import com.example.yukigames.presentation.games.viewModels.favorite_games_viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private var job : Job? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
        favoriteAdapter = FavoriteAdapter()
        binding.recyclerViewFavorites.adapter = favoriteAdapter

        observeViewModel()

    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.state.collect{ state ->

                binding.progressBarFavorite.isVisible = state.isLoading
                binding.errorViewFavorite.isVisible = state.error.isNotBlank()
                binding.errorViewFavorite.text = state.error

                favoriteAdapter.setList(state.games)

            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        _binding = null
    }

}