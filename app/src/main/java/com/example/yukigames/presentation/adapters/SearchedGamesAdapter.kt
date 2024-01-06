package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.SearchItemBinding
import com.example.yukigames.domain.model.Game

class SearchedGamesAdapter : RecyclerView.Adapter<SearchedGamesAdapter.SearchedGamesHolder>() {

    var searchedGameList : List<Game> = emptyList()

    fun setSearchedList(searchedList : List<Game>){
        this.searchedGameList = searchedList
        notifyDataSetChanged()
    }

    class SearchedGamesHolder(val binding : SearchItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindSearchedData(data : Game){

            binding.txtTitleSearch.text = data.name
            binding.txtGenreSearch.text = "Genre"
            binding.txtReleaseDateSearch.text = data.released
            binding.txtVoteAverageSearch.text = data.rating.toString() + "/10"

            Glide.with(binding.posterViewSearch)
                .load(data.background_image)
                .into(binding.posterViewSearch)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedGamesHolder {
        val view = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchedGamesAdapter.SearchedGamesHolder(view)
    }

    override fun getItemCount(): Int {
        return searchedGameList.size
    }

    override fun onBindViewHolder(holder: SearchedGamesHolder, position: Int) {
        holder.bindSearchedData(searchedGameList.get(position))
    }

}