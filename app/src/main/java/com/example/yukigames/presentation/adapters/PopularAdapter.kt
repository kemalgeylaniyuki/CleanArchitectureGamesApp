package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.PopularItemBinding
import com.example.yukigames.domain.model.Game
import com.example.yukigames.presentation.main.views.pages.HomeFragmentDirections

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.GamesHolder>() {

    var gameList : List<Game> = emptyList()

    fun setList(gameList : List<Game>){
        this.gameList = gameList
        notifyDataSetChanged()
    }

    class GamesHolder(val binding : PopularItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Game){

            binding.txtTitle.text = data.name
            binding.txtGenre.text = data.genres?.joinToString(", ") { it.name }

            Glide.with(binding.posterView)
                .load(data.background_image)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val view = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesHolder(view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: GamesHolder, position: Int) {

        holder.bind(gameList.get(position))

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(gameList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

}