package com.example.newstart.domain.usecase

import com.example.newstart.domain.model.CartItem
import com.example.newstart.domain.repository.ProductRepository

class AddToCartUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(item: CartItem): Boolean = repository.addToCart(item)
}