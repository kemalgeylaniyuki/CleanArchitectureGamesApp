package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.PopularItemBinding
import com.example.yukigames.databinding.RecentItemBinding
import com.example.yukigames.domain.model.Game
import com.example.yukigames.presentation.main.views.pages.HomeFragmentDirections

class RecentAdapter : RecyclerView.Adapter<RecentAdapter.RecentHolder>() {

    var gameList : List<Game> = emptyList()

    fun setList(gameList : List<Game>){
        this.gameList = gameList
        notifyDataSetChanged()
    }

    class RecentHolder(val binding : RecentItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Game){

            binding.txtTitle.text = data.name
            binding.txtGenre.text = data.genres?.joinToString(", ") { it.name }
            binding.txtPlatform.text = data.parent_platforms?.joinToString(", ") { it.platform.name }
            binding.txtReleaseDate.text = data.released
            binding.txtVoteAverage.text = data.rating.toString() + "/5"

            Glide.with(binding.posterView)
                .load(data.background_image)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentHolder {
        val view = RecentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentHolder(view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: RecentHolder, position: Int) {

        holder.bind(gameList.get(position))

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(gameList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    /*
    private class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
    */

}