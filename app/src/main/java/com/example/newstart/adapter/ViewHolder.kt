package com.example.newstart.adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.SearchListItemBinding

class ViewHolder(val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: String) {
        binding.itemTextView.text = item
    }

}