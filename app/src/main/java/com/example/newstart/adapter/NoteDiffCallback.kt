package com.example.newstart.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newstart.domain.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem

    override fun getChangePayload(oldItem: Note, newItem: Note): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

}