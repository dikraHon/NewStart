package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newstart.databinding.ItemListLayoutBinding
import com.example.newstart.domain.Products


class ProductAdapter (
    private val onItemChecked: (Products) -> Unit
): ListAdapter < Products, ProductViewHolder > ( ProductDiffCallback() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(
            ItemListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemChecked
        )

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}