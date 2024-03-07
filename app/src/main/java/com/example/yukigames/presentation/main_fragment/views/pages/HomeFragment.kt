package com.example.yukigames.presentation.main_fragment.views.pages


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentHomeBinding
import com.example.yukigames.presentation.base_fragment.BaseFragment
import com.example.yukigames.presentation.adapters.PopularAdapter
import com.example.yukigames.presentation.adapters.RecentAdapter
import com.example.yukigames.presentation.main_fragment.viewmodels.home_viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private lateinit var popularAdapter : PopularAdapter
    private lateinit var recentAdapter: RecentAdapter

    override val viewModel : HomeViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun recyclerViewUpdates(){

        popularAdapter = PopularAdapter()
        recentAdapter = RecentAdapter()

        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewRecent.adapter = recentAdapter

    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.statePopular.collect { popular ->

                // Update UI based on the state
                binding.progressBar.isVisible = popular.isLoading
                binding.errorView.isVisible = popular.error.isNotBlank()
                binding.errorView.text = popular.error

                // Update game list
                popularAdapter.setList(popular.popularGames)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateRecent.collect { recent ->

                // Update UI based on the state
                binding.progressBar.isVisible = recent.isLoading
                binding.errorView.isVisible = recent.error.isNotBlank()
                binding.errorView.text = recent.error

                // Update game list
                recentAdapter.setList(recent.recentGames)
            }
        }
    }

}