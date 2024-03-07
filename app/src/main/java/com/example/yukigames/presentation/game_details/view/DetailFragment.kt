package com.example.yukigames.presentation.game_details.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.FragmentGameDetailsBinding
import com.example.yukigames.presentation.game_details.viewModel.GameDetailsViewModel
import com.example.yukigames.util.Constants.CHECKBOX_PREF
import com.example.yukigames.util.Constants.CHECKBOX_STATE_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!

    private val gameDetailsViewModel : GameDetailsViewModel by viewModels()

    private var id = 0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = DetailFragmentArgs.fromBundle(it).id
        }

        gameDetailsViewModel.getGameDetails(id)

        observeViewModel()

        // Keep checkbox status
        val sharedPreferences = requireActivity().getSharedPreferences(CHECKBOX_PREF, Context.MODE_PRIVATE)
        val savedCheckBoxState = sharedPreferences.getBoolean(getCheckBoxStateKey(id), false)
        binding.checkBox.isChecked = savedCheckBoxState


        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            gameDetailsViewModel.state.value.gameDetails?.let {
                gameDetailsViewModel.toggleFavoriteStatus(it, isChecked)

                with(sharedPreferences.edit()){
                    putBoolean(getCheckBoxStateKey(id), isChecked)
                    apply()
                }

                if (isChecked) {
                    Toast.makeText(requireContext(), "Saved to Favorites!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Removed from Favorites!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun getCheckBoxStateKey(id: Int): String {
        return "$CHECKBOX_STATE_KEY$id"
    }

    fun observeViewModel(){

        viewLifecycleOwner.lifecycleScope.launch {
            gameDetailsViewModel.state.collect{

                it.gameDetails?.let {
                    binding.textViewName.text = it.name_original
                    binding.textViewDescription.text = it.description_raw
                    binding.categoryText.text = it.genres?.joinToString(", ") { it.name }
                    binding.platformText.text = it.parent_platforms?.joinToString(", " ) { it.platform.name }
                    binding.textViewReleased.text = it.released

                    val formattedRating : String = "%.1f".format(it.rating)
                    binding.textViewRating.text = formattedRating

                    binding.addedText.text = it.added.toString()

                    binding.textViewWebsite.text = it.website

                    Glide.with(binding.imageView)
                        .load(it.background_image)
                        .into(binding.imageView)


                }


                if (it.error.isNotBlank()){
                    binding.errorTextView.text = it.error
                    binding.errorTextView.visibility = View.VISIBLE
                }
                else {
                    binding.errorTextView.visibility = View.GONE
                }

                if (it.isLoading){
                    binding.progressBar2.visibility = View.VISIBLE
                }
                else {
                    binding.progressBar2.visibility = View.GONE
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}