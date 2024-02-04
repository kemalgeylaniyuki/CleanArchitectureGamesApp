package com.example.yukigames.presentation.games_by_genre.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yukigames.databinding.FragmentGameDetailsBinding
import com.example.yukigames.databinding.FragmentGamesByGenreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesByGenreFragment : Fragment() {

    private var _binding: FragmentGamesByGenreBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}