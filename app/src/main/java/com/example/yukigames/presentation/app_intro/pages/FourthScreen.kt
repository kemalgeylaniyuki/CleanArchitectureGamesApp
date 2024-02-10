package com.example.yukigames.presentation.app_intro.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.yukigames.R
import com.example.yukigames.databinding.FragmentFourthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthScreen : Fragment() {

    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
        }

        return view
    }

    override fun onResume() {

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nextButton)



        /*
        nextButton?.alpha = 1f
        nextButton?.isClickable = true
        */

        nextButton?.alpha = 0f
        nextButton?.isClickable = false

        prevButton?.setOnClickListener {
            viewPager?.currentItem = 2
        }

        /*
        nextButton?.setOnClickListener {
            viewPager?.currentItem = 4
        }
        */

        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}