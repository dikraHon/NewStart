package com.example.newstart.domain

interface NoteRepository {
    fun getNotes(): List<Note>
    fun addNote(text: String)
    fun deleteNote(id: Long)
    fun updateNote(note: Note)
    fun saveNotes()
}