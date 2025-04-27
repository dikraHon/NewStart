package com.example.newstart.presentation

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstart.adapter.NotesAdapter
import com.example.newstart.databinding.ActivityMainBinding
import com.example.newstart.databinding.DialogEditNoteBinding
import com.example.newstart.domain.Note


class MainActivity : AppCompatActivity() {

    private var bindingDialogLayout: DialogEditNoteBinding? = null
    private var binding: ActivityMainBinding? = null
    private val viewModel: NotesViewModel by viewModels()
    private var adapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupViewActivity()
    }

    private fun setupViewActivity() {
        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter(
            onDeleteClick = { noteId ->
                viewModel.deleteNote(noteId)
            },
            onEditClick = { noteEd ->
                viewModel.startEditing(noteEd)
                showEditDialog(noteEd)
            }
        )
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter
    }

    private fun setupListeners() {
        binding?.addNoteButton?.setOnClickListener {
            val text = binding?.editTextNote?.text.toString()
            viewModel.addNote(text)
            binding?.editTextNote?.text?.clear()
        }
    }

    private fun showEditDialog(note: Note) {

        bindingDialogLayout = DialogEditNoteBinding.inflate(layoutInflater)
        val editText = bindingDialogLayout?.editText
        editText?.setText(note.text)

        AlertDialog.Builder(this)
            .setTitle("Edit note")
            .setView(bindingDialogLayout?.root)

            .setPositiveButton("Save") { _, _ ->
                val newText = editText?.text.toString()
                if (newText.isNotBlank()) {
                    viewModel.updateNote(note.copy(text = newText))
                }
            }

            .setNegativeButton("Close") { _, _ ->
                viewModel.cancelEditing()
            }

            .setOnDismissListener {
                viewModel.cancelEditing()
            }

            .show()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(this) { notes ->
            adapter?.submitList(notes)
        }
    }

}