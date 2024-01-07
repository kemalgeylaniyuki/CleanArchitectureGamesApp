package com.example.yukigames.presentation.games.views.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yukigames.databinding.FragmentSearchBinding
import com.example.yukigames.presentation.adapters.SearchedGamesAdapter
import com.example.yukigames.presentation.game_details.view.DetailFragmentArgs
import com.example.yukigames.presentation.game_details.viewModel.GameDetailsViewModel
import com.example.yukigames.presentation.games.search_viewmodel.SearchEvent
import com.example.yukigames.presentation.games.search_viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchedGamesAdapter: SearchedGamesAdapter
    private val searchViewModel : SearchViewModel by viewModels()
    private val gameDetailsViewModel : GameDetailsViewModel by viewModels()
    private var job : Job? = null
    private var id = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = DetailFragmentArgs.fromBundle(it).id
        }

        gameDetailsViewModel.getGameDetails(id)

        binding.recyclerViewSearching.layoutManager = LinearLayoutManager(context)
        searchedGamesAdapter = SearchedGamesAdapter()
        binding.recyclerViewSearching.adapter = searchedGamesAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Kullanıcı arama butonuna tıkladığında çalışır.
                // Bu kısmı boş bırakılabilir, çünkü otomatik arama yapmak isteniyor.
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Kullanıcı arama sorgusunu her değiştirdiğinde çalışır
                if (!newText.isNullOrBlank()){
                    searchViewModel.onEvent(SearchEvent.Search(newText))
                }
                return true
            }

        })

        binding.searchView.setOnCloseListener { true }

        observeViewModel()

    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.state.collect { state ->

                // Update UI based on the state
                binding.progressBarFromSearch.isVisible = state.isLoading
                binding.errorTextFromSearch.isVisible = state.error.isNotBlank()
                binding.errorTextFromSearch.text = state.error

                // Update searched games list
                searchedGamesAdapter.setSearchedList(state.searched_games)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        _binding = null
    }

}