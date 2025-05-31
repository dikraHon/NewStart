package com.example.newstart.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newstart.domain.model.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

}