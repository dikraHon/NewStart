package com.example.newstart.authorAdapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.authorAdapter.data.Item
import com.example.newstart.databinding.AuthorLayoutBinding

class AuthorViewHolder(val binding: AuthorLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Item.Author) {
        binding.nameTextView.text = item.name
        binding.textTextView.text = item.text
    }

}