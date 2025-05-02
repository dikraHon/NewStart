package com.example.newstart.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newstart.data.model.Anime

class AnimeDiffUtil: DiffUtil.ItemCallback < Anime > () {

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
        oldItem.id == newItem.id


}