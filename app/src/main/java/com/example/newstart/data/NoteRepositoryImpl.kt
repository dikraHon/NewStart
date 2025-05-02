package com.example.newstart.data

import android.content.Context
import com.example.newstart.data.fileData.FileStorageManager
import com.example.newstart.domain.Note
import com.example.newstart.domain.NoteRepository

class NoteRepositoryImpl (
    private val context: Context,
    private val fileStorage: FileStorageManager = FileStorageManager(context)
) : NoteRepository {
    private var notes = mutableListOf<Note>()
    private var nextId: Long = 1

    init {
        loadNotes()
    }

    override fun getNotes(): List<Note> = notes.toList()

    override fun addNote(text: String) {
        notes.add(Note(nextId++, text))
        saveNotes()
    }

    override fun deleteNote(id: Long) {
        notes.removeIf { it.id == id }
        saveNotes()
    }

    override fun updateNote(note: Note) {
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
            saveNotes()
        }
    }

    override fun saveNotes() {
        fileStorage.saveNotes(notes.map { it.toEntity() })
    }

    private fun loadNotes() {
        val loadedNotes = fileStorage.loadNotes().map { it.toNote() }
        notes = loadedNotes.toMutableList()
        nextId = (notes.maxOfOrNull { it.id } ?: 0) + 1
    }

}