package com.example.newstart.domain.useCase

import com.example.newstart.domain.Note
import com.example.newstart.domain.NoteRepository

class GetAllNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): List<Note> = repository.getNotes()
}