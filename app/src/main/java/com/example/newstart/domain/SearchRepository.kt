package com.example.newstart.domain

interface SearchRepository {

    suspend fun search (query: String) : List<String>

}