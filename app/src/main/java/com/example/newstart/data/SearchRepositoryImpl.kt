package com.example.newstart.data

import com.example.newstart.domain.SearchData
import com.example.newstart.domain.SearchRepository
import kotlinx.coroutines.delay

class SearchRepositoryImpl(
    private val data: SearchDataSource = SearchDataSource()
): SearchRepository  {

    override suspend fun search(query: String): List<String> {
        return data.search(query)
    }

}