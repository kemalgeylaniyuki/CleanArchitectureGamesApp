package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.SearchItemBinding
import com.example.yukigames.domain.model.Game
import com.example.yukigames.presentation.games.views.pages.HomeFragmentDirections
import com.example.yukigames.presentation.games.views.pages.SearchFragmentDirections

class SearchedGamesAdapter : RecyclerView.Adapter<SearchedGamesAdapter.SearchedGamesHolder>() {

    var searchedGameList : List<Game> = emptyList()

    fun setSearchedList(searchedList : List<Game>){
        this.searchedGameList = searchedList
        notifyDataSetChanged()
    }

    class SearchedGamesHolder(val binding : SearchItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindSearchedData(data : Game){

            binding.txtTitleSearch.text = data.name
            binding.txtGenreSearch.text = data.genres?.map { it.name }.toString()
            binding.txtReleaseDateSearch.text = data.released
            binding.txtVoteAverageSearch.text = data.rating.toString() + "/10"

            Glide.with(binding.posterViewSearch)
                .load(data.background_image)
                .into(binding.posterViewSearch)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedGamesHolder {
        val view = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchedGamesHolder(view)
    }

    override fun getItemCount(): Int {
        return searchedGameList.size
    }

    override fun onBindViewHolder(holder: SearchedGamesHolder, position: Int) {
        holder.bindSearchedData(searchedGameList.get(position))

        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(searchedGameList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }

    }

}