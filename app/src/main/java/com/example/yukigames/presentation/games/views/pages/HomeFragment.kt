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
import com.example.yukigames.databinding.FragmentHomeBinding
import com.example.yukigames.presentation.adapters.GamesAdapter
import com.example.yukigames.presentation.games.gamelist_viewmodel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var gamesAdapter : GamesAdapter
    private val moviesViewModel : GamesViewModel by viewModels()
    private var job : Job? = null

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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        gamesAdapter = GamesAdapter()
        binding.recyclerView.adapter = gamesAdapter

        observeViewModel()

    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.state.collect { state ->
                // Update UI based on the state
                binding.progressBar.isVisible = state.isLoading
                binding.errorView.isVisible = state.error.isNotBlank()
                binding.errorView.text = state.error

                // Update game list
                gamesAdapter.setList(state.games)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        _binding = null
    }

}