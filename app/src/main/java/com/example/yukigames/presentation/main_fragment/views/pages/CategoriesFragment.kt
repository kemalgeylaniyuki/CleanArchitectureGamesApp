package com.example.yukigames.presentation.main_fragment.views.pages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yukigames.databinding.FragmentCategoriesBinding
import com.example.yukigames.presentation.base_fragment.BaseFragment
import com.example.yukigames.presentation.adapters.CategoriesAdaper
import com.example.yukigames.presentation.main_fragment.viewmodels.categories_viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesViewModel>() {

    private lateinit var categoriesAdaper : CategoriesAdaper

    override val viewModel : CategoriesViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(inflater, container, false)
    }

    override fun recyclerViewUpdates() {
        categoriesAdaper = CategoriesAdaper()
        binding.recyclerViewCategories.adapter = categoriesAdaper
    }

    override fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                // Update UI based on the state
                binding.progressBarCategory.isVisible = state.isLoading
                binding.errorViewCategory.isVisible = state.error.isNotBlank()
                binding.errorViewCategory.text = state.error

                // Update game list
                categoriesAdaper.setList(state.genres)
            }
        }

    }

}