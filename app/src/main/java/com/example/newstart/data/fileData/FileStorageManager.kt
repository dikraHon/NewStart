package com.example.newstart.data.fileData

import android.content.Context
import com.example.newstart.data.NoteEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class FileStorageManager(context: Context) {

    private val gson = Gson()
    private val notesFile = File(context.filesDir, "notes.json")

    fun saveNotes(notes: List<NoteEntity>) {
        try {
            notesFile.writeText(gson.toJson(notes))
        } catch (e: Exception) {
            throw FileStorageException("Failed to save notes", e)
        }
    }

    fun loadNotes(): List<NoteEntity> {
        if (!notesFile.exists()) return emptyList()

        return try {
            val notesJson = notesFile.readText()
            gson.fromJson(notesJson, object : TypeToken<List<NoteEntity>>() { }.type)
                ?: emptyList()
        } catch (e: Exception) {
            throw FileStorageException("Failed to load notes", e)
        }
    }

}