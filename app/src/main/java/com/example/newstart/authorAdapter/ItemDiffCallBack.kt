package com.example.newstart.authorAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newstart.authorAdapter.data.Item

class ItemDiffCallBack(private val oldList: List<Item>, private val newList: List<Item>): DiffUtil.Callback() {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem::class != newItem::class) return false

        return when (oldItem) {
            is Item.Author -> oldItem.id == (newItem as Item.Author).id
            is Item.Image -> oldItem.id == (newItem as Item.Image).id
            is Item.TextWithButton -> oldItem.id == (newItem as Item.TextWithButton).id
        }
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getNewListSize(): Int = newList.size

    override fun getOldListSize(): Int = oldList.size
}