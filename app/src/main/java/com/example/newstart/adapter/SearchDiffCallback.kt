package com.example.newstart.adapter

import androidx.recyclerview.widget.DiffUtil

class SearchDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

}