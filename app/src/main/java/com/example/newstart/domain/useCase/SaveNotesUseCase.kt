package com.example.newstart.domain.useCase

import com.example.newstart.domain.NoteRepository

class SaveNotesUseCase (private val repository: NoteRepository) {
    operator fun invoke() = repository.saveNotes()
}