package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newstart.data.model.Anime
import com.example.newstart.databinding.ItemAnimeBinding

class AnimeAdapter (
    private val onItemClick: (Anime) -> Unit
) : ListAdapter < Anime, AnimeViewHolder > ( AnimeDiffUtil() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {

        return AnimeViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ) ,
            onItemClick
        )

    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
        holder.itemView.setOnClickListener { onItemClick(anime) }
    }

}