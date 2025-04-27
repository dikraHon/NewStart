package com.example.newstart.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.ItemNotesLayoutBinding
import com.example.newstart.domain.Note

class NoteViewHolder(
    private val onDeleteClick: (Long) -> Unit,
    private val onEditClick: (Note) -> Unit,
    private val binding: ItemNotesLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.tvContent.text = note.text
        binding.tvDate.text = note.date

        binding.btnDelete.setOnClickListener {
            onDeleteClick(note.id)
        }

        binding.root.setOnClickListener {
            onEditClick(note)
        }
    }
}