package com.example.newstart.domain

import com.example.newstart.domain.DataRes

interface DataRepository {
    suspend fun fetchData() : DataRes
}