package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.SearchListItemBinding

class SearchRecyclerAdapter  : ListAdapter<String, ViewHolder>( SearchDiffCallback() ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {

        return ViewHolder(
            SearchListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}
