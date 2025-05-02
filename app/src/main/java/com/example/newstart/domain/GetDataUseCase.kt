package com.example.newstart.domain

class GetDataUseCase (private val repository: DataRepository) {
    suspend operator fun invoke() : DataRes {
        return repository.fetchData()
    }
}