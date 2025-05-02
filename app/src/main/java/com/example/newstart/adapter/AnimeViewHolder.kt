package com.example.newstart.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newstart.data.model.Anime
import com.example.newstart.databinding.ItemAnimeBinding

class AnimeViewHolder (
    val binding: ItemAnimeBinding,
    private val onItemClick: (Anime) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(anime: Anime) {

        binding.titleTextView.text = anime.title
        binding.animeImageView.load(anime.images.jpg.image_url)

    }

}