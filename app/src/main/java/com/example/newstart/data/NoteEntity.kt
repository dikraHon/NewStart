package com.example.newstart.data

import com.example.newstart.domain.Note
import com.example.newstart.presentation.UiNote

data class NoteEntity(
    val id: Long,
    val text: String,
    val date: String
) {
    /*
    *реобразование в сущность для хранения
    */
    fun toNote() = Note(id, text, date)
    fun toUiNote() = UiNote(id, text, date)
}

fun Note.toEntity() = NoteEntity(id, text, date)