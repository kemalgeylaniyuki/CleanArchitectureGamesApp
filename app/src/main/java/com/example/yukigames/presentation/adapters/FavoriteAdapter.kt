package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.GamesItem2Binding
import com.example.yukigames.domain.model.Game
import com.example.yukigames.presentation.games.views.pages.FavoritesFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emptyFlow

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    var gameList : List<Game> = emptyList()

    fun setList(gameList : List<Game>){
        this.gameList = gameList
        notifyDataSetChanged()
    }

    class FavoriteHolder(val binding: GamesItem2Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Game){

            binding.txtTitle.text = data.name
            binding.txtGenre.text = data.genres?.joinToString(", ") { it.name }
            binding.txtPlatform.text = data.parent_platforms?.joinToString(", ") { it.platform.name }
            binding.txtReleaseDate.text = data.released
            binding.txtVoteAverage.text = data.rating.toString() + "/10"

            Glide.with(binding.posterView)
                .load(data.background_image)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val view = GamesItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(gameList.get(position))

        holder.itemView.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(gameList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

}