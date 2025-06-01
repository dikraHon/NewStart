package com.example.newstart.data

import kotlinx.coroutines.delay

class SearchDataSource {
    suspend fun search (query : String) : List<String> {
        delay(1000)
        if (query == "error") throw RuntimeException("Unknown error")

        return listOf(query)
    }
}