package com.example.newstart.authorAdapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstart.authorAdapter.data.Item
import com.example.newstart.databinding.ImageLayoutBinding

class ImageViewHolder(val binding: ImageLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Item.Image) {
        binding.imageView.setImageResource(item.urlImage)
        binding.text2TextView.text = item.text
    }

}