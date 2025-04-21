package com.example.newstart.authorAdapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.authorAdapter.data.Item
import com.example.newstart.databinding.ButtonTextLayoutBinding

class TextWithButtonViewHolder(val binding: ButtonTextLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Item.TextWithButton) {

        binding.text3TextView.text = item.text
        binding.changeItemButton.setOnClickListener {
            binding.text3TextView.text = binding.text3TextView.text.reversed()
        }

    }

}