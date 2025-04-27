package com.example.newstart.domain

import com.example.newstart.presentation.UiNote
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: Long,
    val text: String,
    val date: String = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())
) {
    fun toUiNote() = UiNote (id, text, date) // Обратное преобразование
}