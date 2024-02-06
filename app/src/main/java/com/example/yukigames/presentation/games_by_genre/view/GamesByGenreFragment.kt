package com.example.yukigames.presentation.games_by_genre.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yukigames.databinding.FragmentGamesByGenreBinding
import com.example.yukigames.presentation.adapters.GamesByGenreAdapter
import com.example.yukigames.presentation.games_by_genre.viewmodel.GamesByGenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesByGenreFragment : Fragment() {

    private var _binding: FragmentGamesByGenreBinding? = null
    private val binding get() = _binding!!

    private lateinit var gamesByGenreAdapter : GamesByGenreAdapter
    private val gamesByGenreViewModel : GamesByGenreViewModel by viewModels()
    private var job : Job? = null
    var genre : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGamesByGenreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewGameByGenre.layoutManager = LinearLayoutManager(context)
        gamesByGenreAdapter = GamesByGenreAdapter()
        binding.recyclerViewGameByGenre.adapter = gamesByGenreAdapter


        arguments?.let {
            genre = GamesByGenreFragmentArgs.fromBundle(it).genre
        }

        gamesByGenreViewModel.getGamesByGenre("1",genre)

        observeViewModel()
    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            gamesByGenreViewModel.state.collect { state ->

                // Update UI based on the state
                binding.progressBarGameByGenre.isVisible = state.isLoading
                binding.errorViewGameByGenre.isVisible = state.error.isNotBlank()
                binding.errorViewGameByGenre.text = state.error

                /*
                if(state.genre != genre){
                    gamesByGenreViewModel.getGamesByGenre(state.page,genre)
                }

                 */

                // Update game list
                gamesByGenreAdapter.setGamesByGenreList(state.games)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        _binding = null
    }

}