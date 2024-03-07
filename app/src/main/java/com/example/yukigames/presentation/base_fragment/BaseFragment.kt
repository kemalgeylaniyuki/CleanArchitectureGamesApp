package com.example.yukigames.presentation.base_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB: ViewBinding, VM: ViewModel> : Fragment(), SpecificFeature {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUpdates()

        observeViewModel()

        specificFeature()

    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun recyclerViewUpdates()

    abstract fun observeViewModel()

    override fun specificFeature(){

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}