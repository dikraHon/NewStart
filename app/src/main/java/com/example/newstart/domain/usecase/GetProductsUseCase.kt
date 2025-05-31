package com.example.newstart.domain.usecase

import com.example.newstart.domain.model.Product
import com.example.newstart.domain.repository.ProductRepository

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}