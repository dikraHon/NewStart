package com.example.newstart.domain.useCase

import com.example.newstart.domain.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(text: String) = repository.addNote(text)
}