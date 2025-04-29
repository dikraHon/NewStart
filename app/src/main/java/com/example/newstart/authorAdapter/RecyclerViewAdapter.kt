package com.example.newstart.authorAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.authorAdapter.data.Item
import com.example.newstart.authorAdapter.viewHolder.AuthorViewHolder
import com.example.newstart.authorAdapter.viewHolder.ImageViewHolder
import com.example.newstart.authorAdapter.viewHolder.TextWithButtonViewHolder
import com.example.newstart.databinding.AuthorLayoutBinding
import com.example.newstart.databinding.ButtonTextLayoutBinding
import com.example.newstart.databinding.ImageLayoutBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Item> = emptyList()

    companion object {
        private const val TYPE_AUTHOR = 0
        private const val TYPE_IMAGE = 1
        private const val TYPE_TEXT_WITH_BUTTON = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Item.Author -> TYPE_AUTHOR
            is Item.Image -> TYPE_IMAGE
            is Item.TextWithButton -> TYPE_TEXT_WITH_BUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_AUTHOR -> {
                val binding = AuthorLayoutBinding.inflate(layoutInflater, parent, false)
                AuthorViewHolder(binding)
            }
            TYPE_IMAGE -> {
                val binding = ImageLayoutBinding.inflate(layoutInflater, parent, false)
                ImageViewHolder(binding)
            }
            TYPE_TEXT_WITH_BUTTON -> {
                val binding = ButtonTextLayoutBinding.inflate(layoutInflater, parent, false)
                TextWithButtonViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = items[position]) {
            is Item.Author -> (holder as AuthorViewHolder).onBind(item)
            is Item.Image -> (holder as ImageViewHolder).onBind(item)
            is Item.TextWithButton -> (holder as TextWithButtonViewHolder).onBind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        val diffResult = DiffUtil.calculateDiff(ItemDiffCallBack(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

}