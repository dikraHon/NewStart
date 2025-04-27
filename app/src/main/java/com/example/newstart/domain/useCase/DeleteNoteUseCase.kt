package com.example.newstart.domain.useCase

import com.example.newstart.domain.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(id: Long) = repository.deleteNote(id)
}