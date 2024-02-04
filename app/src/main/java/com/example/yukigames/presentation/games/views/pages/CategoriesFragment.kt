package com.example.yukigames.presentation.games.views.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yukigames.databinding.FragmentCategoriesBinding
import com.example.yukigames.databinding.FragmentFavoritesBinding
import com.example.yukigames.presentation.adapters.CategoriesAdaper
import com.example.yukigames.presentation.adapters.GamesAdapter
import com.example.yukigames.presentation.games.viewModels.gamelist_viewmodel.GamesViewModel
import com.example.yukigames.presentation.games.viewModels.genres_viewmodel.GenresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoriesAdaper : CategoriesAdaper
    private val genresViewModel : GenresViewModel by viewModels()
    private var job : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCategories.layoutManager = GridLayoutManager(context,2)
        categoriesAdaper = CategoriesAdaper()
        binding.recyclerViewCategories.adapter = categoriesAdaper

        observeViewModel()

    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            genresViewModel.state.collect { state ->
                // Update UI based on the state
                binding.progressBarCategory.isVisible = state.isLoading
                binding.errorViewCategory.isVisible = state.error.isNotBlank()
                binding.errorViewCategory.text = state.error

                // Update game list
                categoriesAdaper.setList(state.genres)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        _binding = null
    }

}