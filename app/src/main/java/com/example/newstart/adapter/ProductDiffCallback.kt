package com.example.newstart.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newstart.domain.Products

class ProductDiffCallback: DiffUtil.ItemCallback<Products>() {

    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }

}