package com.example.newstart.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newstart.data.NoteRepositoryImpl
import com.example.newstart.domain.useCase.AddNoteUseCase
import com.example.newstart.domain.useCase.DeleteNoteUseCase
import com.example.newstart.domain.useCase.GetAllNotesUseCase
import com.example.newstart.domain.Note
import com.example.newstart.domain.NoteRepository
import com.example.newstart.domain.useCase.SaveNotesUseCase
import com.example.newstart.domain.useCase.UpdateNoteUseCase

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository = NoteRepositoryImpl(application)

    private val getNotesUseCase = GetAllNotesUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val saveNotesUseCase = SaveNotesUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val updateNoteUseCase = UpdateNoteUseCase(repository)

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val noteToEdit = MutableLiveData<Note?>()

    fun startEditing(note: Note) {
        noteToEdit.value = note
    }

    fun updateNote(updatedNote: Note) {
        updateNoteUseCase(updatedNote)
        noteToEdit.value = null
        loadNotes()
    }

    fun cancelEditing() {
        noteToEdit.value = null
    }

    init {
        loadNotes()
    }

    fun deleteNote(id: Long) {
        deleteNoteUseCase(id)
        loadNotes()
    }

    fun addNote(text: String) {
        if (text.isBlank()) return
        addNoteUseCase(text)
        saveNotesUseCase()
        loadNotes()
    }

    private fun loadNotes() {
        _notes.value = getNotesUseCase()
    }
}