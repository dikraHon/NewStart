package com.example.newstart.domain.repository

import com.example.newstart.domain.model.CartItem
import com.example.newstart.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetails(id: Int): Product
    suspend fun addToCart(item: CartItem): Boolean
    suspend fun removeFromCart(productId: Int): Boolean
    suspend fun updateProduct(product: Product): Product
}