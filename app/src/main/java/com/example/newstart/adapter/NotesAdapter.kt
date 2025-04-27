package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newstart.databinding.ItemNotesLayoutBinding
import com.example.newstart.domain.Note

class NotesAdapter(
    private val onDeleteClick: (Long) -> Unit,
    private val onEditClick: (Note) -> Unit
) : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNotesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder (
            onDeleteClick,
            onEditClick,
            binding
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}