package com.example.yukigames.presentation.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yukigames.R
import com.example.yukigames.databinding.FragmentMainBinding
import com.example.yukigames.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root


        if (sessionManager.getIsFirstRun())
            sessionManager.setIsFirstRun(false)

        setUpTabBar()

        return view
    }

    private fun setUpTabBar(){

        binding.bottomNavBar.setItemSelected(R.id.home, true)
        binding.bottomNavBar.setOnItemSelectedListener {
            when(it){
                R.id.home -> childFragmentManager.primaryNavigationFragment?.
                findNavController()?.navigate(R.id.homeFragment)
                R.id.favorites -> childFragmentManager.primaryNavigationFragment?.
                findNavController()?.navigate(R.id.favoritesFragment)
                R.id.settings -> childFragmentManager.primaryNavigationFragment?.
                findNavController()?.navigate(R.id.searchFragment)
                R.id.category -> childFragmentManager.primaryNavigationFragment?.
                findNavController()?.navigate(R.id.categoriesFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}