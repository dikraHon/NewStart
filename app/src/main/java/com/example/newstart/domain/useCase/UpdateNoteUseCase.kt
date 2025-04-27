package com.example.newstart.domain.useCase

import com.example.newstart.domain.Note
import com.example.newstart.domain.NoteRepository

class UpdateNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.updateNote(note)
}