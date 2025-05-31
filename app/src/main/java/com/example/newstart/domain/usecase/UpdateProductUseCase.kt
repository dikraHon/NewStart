package com.example.newstart.domain.usecase

import com.example.newstart.domain.model.Product
import com.example.newstart.domain.repository.ProductRepository

class UpdateProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(product: Product): Product = repository.updateProduct(product)
}