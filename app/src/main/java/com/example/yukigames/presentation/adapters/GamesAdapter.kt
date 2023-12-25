package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.GamesItem2Binding
import com.example.yukigames.domain.model.Game

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.GamesHolder>() {

    var gameList : List<Game> = emptyList()

    fun setList(gameList : List<Game>){
        this.gameList = gameList
        notifyDataSetChanged()
    }

    class GamesHolder(val binding : GamesItem2Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Game){

            binding.txtTitle.text = data.name
            binding.txtGenre.text = "Genre"
            binding.txtReleaseDate.text = data.released
            binding.txtVoteAverage.text = data.rating.toString() + "/10"

            Glide.with(binding.posterView)
                .load(data.background_image)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val view = GamesItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesHolder(view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: GamesHolder, position: Int) {
        holder.bind(gameList.get(position))
    }

}