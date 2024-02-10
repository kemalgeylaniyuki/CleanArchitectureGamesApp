package com.example.yukigames.presentation.main.views.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yukigames.databinding.FragmentHomeBinding
import com.example.yukigames.presentation.adapters.PopularAdapter
import com.example.yukigames.presentation.adapters.RecentAdapter
import com.example.yukigames.presentation.main.viewmodels.home_viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularAdapter : PopularAdapter
    private lateinit var recentAdapter: RecentAdapter
    private val gamesViewModel : HomeViewModel by viewModels()
    private var job1 : Job? = null
    private var job2 : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUpdates()

        observeViewModel()

        //gamesViewModel.getRecentGamesPaging("2020-02-01,2024-03-01", "-released")

    }

    fun recyclerViewUpdates(){

        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewRecent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        popularAdapter = PopularAdapter()
        recentAdapter = RecentAdapter()

        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewRecent.adapter = recentAdapter

    }

    fun observeViewModel(){

        job1 = viewLifecycleOwner.lifecycleScope.launch {
            gamesViewModel.statePopular.collect { popular ->

                // Update UI based on the state
                binding.progressBar.isVisible = popular.isLoading
                binding.errorView.isVisible = popular.error.isNotBlank()
                binding.errorView.text = popular.error

                // Update game list
                popularAdapter.setList(popular.popularGames)
            }
        }

        job2 = viewLifecycleOwner.lifecycleScope.launch {
            gamesViewModel.stateRecent.collect { recent ->

                // Update UI based on the state
                binding.progressBar.isVisible = recent.isLoading
                binding.errorView.isVisible = recent.error.isNotBlank()
                binding.errorView.text = recent.error

                // Update game list
                recentAdapter.setList(recent.recentGames)
            }
        }

        /*
        job2 = viewLifecycleOwner.lifecycleScope.launch {
            gamesViewModel.stateRecent.collect { recent ->

                binding.progressBar.isVisible = recent.isLoading
                binding.errorView.isVisible = recent.error.isNotBlank()
                binding.errorView.text = recent.error

                recentAdapter.submitData(viewLifecycleOwner.lifecycle, recent.recentGames)
            }
        }
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        job1?.cancel()
        job2?.cancel()
        _binding = null
    }

}