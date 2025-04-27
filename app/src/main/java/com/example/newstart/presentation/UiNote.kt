package com.example.newstart.presentation

import com.example.newstart.domain.Note

data class UiNote (
    val id: Long,
    val text: String,
    val date: String
) {
    fun toNote() = Note (id, text, date) // Преобразование в UI-модель
}