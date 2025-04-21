package com.example.newstart.domain.useCase

import com.example.newstart.data.ProductRepository
import com.example.newstart.domain.ModelProductsRepository
import com.example.newstart.domain.Products

class GetUseCase(private val repository: ModelProductsRepository) {
    operator fun invoke(): List<Products> = repository.getProducts()
}