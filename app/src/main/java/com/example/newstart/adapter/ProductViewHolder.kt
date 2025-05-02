package com.example.newstart.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.ItemListLayoutBinding
import com.example.newstart.domain.Products

class ProductViewHolder (
    val binding: ItemListLayoutBinding,
    private val onItemChecked: (Products) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun onBind (products: Products) {
        binding.apply {
            name.text = products.name
            itemCheckbox.isChecked = products.state

            itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                onItemChecked(products.copy(state = isChecked))
            }
        }
    }
}