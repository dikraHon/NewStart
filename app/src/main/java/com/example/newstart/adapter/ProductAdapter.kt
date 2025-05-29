package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newstart.data.Product
import com.example.newstart.databinding.ItemProductBinding

class ProductAdapter(
    private val onAddToCart: (Int) -> Unit,
    private val onEdit: (Product) -> Unit
) : ListAdapter<Product, ProductViewHolder>( ProductDiffCallback() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onAddToCart, onEdit)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}