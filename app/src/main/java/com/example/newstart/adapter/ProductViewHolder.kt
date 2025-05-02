package com.example.newstart.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newstart.data.Product
import com.example.newstart.databinding.ItemProductBinding

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onAddToCart: (Int) -> Unit,
    private val onEdit: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.tvTitle.text = product.title
        binding.tvPrice.text = "$${product.price}"
        binding.ivProduct.load(product.image)
        binding.root.setOnClickListener { onAddToCart(product.id) }
        binding.btnEdit.setOnClickListener { onEdit(product) }
    }
}