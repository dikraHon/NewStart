package com.example.newstart.domain

class SearchUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke (query: String) : List<String> = repository.search(query)

}