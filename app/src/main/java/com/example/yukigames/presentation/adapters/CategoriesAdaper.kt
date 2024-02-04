package com.example.yukigames.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yukigames.databinding.GamesItem1Binding
import com.example.yukigames.domain.model.Genres
import com.example.yukigames.presentation.games.views.pages.CategoriesFragmentDirections

class CategoriesAdaper : RecyclerView.Adapter<CategoriesAdaper.CategoryHolder>() {

    var cagetoriesList : List<Genres> = emptyList()

    fun setList(cagetoriesList : List<Genres>){
        this.cagetoriesList = cagetoriesList
        notifyDataSetChanged()
    }

    class CategoryHolder(val binding : GamesItem1Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Genres){

            //binding.txtTitle.text = data.genres?.joinToString(", ") { it.name }
            binding.txtTitle.text = data.name
            Glide.with(binding.posterView)
                .load(data.image_background)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = GamesItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return cagetoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {

        holder.bind(cagetoriesList.get(position))


        holder.itemView.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToGamesByGenreFragment(cagetoriesList.get(position).name)
            Navigation.findNavController(it).navigate(action)
        }



    }

}