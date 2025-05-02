package com.example.newstart.data

import com.example.newstart.domain.DataRepository
import com.example.newstart.domain.DataRes
import kotlinx.coroutines.delay
import kotlin.random.Random

class Repository: DataRepository {

    override suspend fun fetchData(): DataRes {
        return try {
            delay(2000)
            if (Random.nextFloat() < 0.3f) {
                throw Exception("Network request failed")
            }
            DataRes.Success("Data loaded successfully at ${System.currentTimeMillis()}")
        } catch (e: Exception) {
            DataRes.Error(e.message ?: "Unknown error")
        }
    }

}