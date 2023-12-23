package com.example.yukigames.presentation.app_intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yukigames.databinding.FragmentAppIntroBinding
import com.example.yukigames.presentation.adapters.ViewPagerAdapter
import com.example.yukigames.presentation.app_intro.pages.FifthScreen
import com.example.yukigames.presentation.app_intro.pages.FirstScreen
import com.example.yukigames.presentation.app_intro.pages.FourthScreen
import com.example.yukigames.presentation.app_intro.pages.SecondScreen
import com.example.yukigames.presentation.app_intro.pages.ThirdScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppIntroFragment : Fragment() {

    private var _binding: FragmentAppIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppIntroBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentList = arrayListOf(FirstScreen(), SecondScreen(), ThirdScreen(), FourthScreen(), FifthScreen())

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
