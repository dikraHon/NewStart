package com.example.newstart.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newstart.domain.model.Product
import com.example.newstart.databinding.ItemProductBinding

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onItemClick: (Product) -> Unit,
    private val onAddToCart: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.titleText.text = product.title
        binding.priceText.text = "$${product.price}"
        binding.productImage.load(product.imageUrl)
        binding.root.setOnClickListener { onItemClick(product) }
        binding.addToCartButton.setOnClickListener { onAddToCart(product) }
    }
}