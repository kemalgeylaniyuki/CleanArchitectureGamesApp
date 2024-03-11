package com.example.yukigames.presentation.main_fragment.views.pages

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentSearchBinding
import com.example.yukigames.presentation.BaseFragment
import com.example.yukigames.presentation.adapters.SearchedGamesAdapter
import com.example.yukigames.presentation.main_fragment.viewmodels.search_viewmodel.SearchEvent
import com.example.yukigames.presentation.main_fragment.viewmodels.search_viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private lateinit var searchedGamesAdapter: SearchedGamesAdapter

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getViewBinding(): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUpViews() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Kullanıcı arama butonuna tıkladığında çalışır.
                // Bu kısmı boş bırakılabilir, çünkü otomatik arama yapmak isteniyor.
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Kullanıcı arama sorgusunu her değiştirdiğinde çalışır
                if (!newText.isNullOrBlank()){
                    viewModel.onEvent(SearchEvent.Search(newText))
                }
                return true
            }

        })

        binding.searchView.setOnCloseListener { true }

        searchedGamesAdapter = SearchedGamesAdapter()
        binding.recyclerViewSearching.adapter = searchedGamesAdapter
    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateSearch.collect { state ->

                // Update UI based on the state
                binding.progressBarFromSearch.isVisible = state.isLoading
                binding.errorTextFromSearch.isVisible = state.error.isNotBlank()
                binding.errorTextFromSearch.text = state.error

                // Update searched games list
                searchedGamesAdapter.setSearchedList(state.searched_games)

            }
        }

    }

}