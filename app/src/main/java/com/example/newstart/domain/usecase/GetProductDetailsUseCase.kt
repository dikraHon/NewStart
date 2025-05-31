package com.example.newstart.domain.usecase

import com.example.newstart.domain.model.Product
import com.example.newstart.domain.repository.ProductRepository

class GetProductDetailsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(id: Int): Product = repository.getProductDetails(id)
}