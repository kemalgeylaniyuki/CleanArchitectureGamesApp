package com.example.yukigames.presentation.games.views.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yukigames.databinding.FragmentCategoriesBinding
import com.example.yukigames.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}