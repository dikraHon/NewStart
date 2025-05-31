package com.example.newstart.data.repository

import com.example.newstart.data.api.FakeStoreApi
import com.example.newstart.data.dto.ProductDto
import com.example.newstart.data.dto.RatingDto
import com.example.newstart.domain.model.CartItem
import com.example.newstart.domain.model.Product
import com.example.newstart.domain.repository.ProductRepository
import jakarta.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().map { it.toDomain() }
    }

    override suspend fun getProductDetails(id: Int): Product {
        return api.getProductDetails(id).toDomain()
    }

    override suspend fun addToCart(item: CartItem): Boolean {
        return api.addToCart(item).isSuccessful
    }

    override suspend fun removeFromCart(productId: Int): Boolean {
        return api.removeFromCart(productId).isSuccessful
    }

    override suspend fun updateProduct(product: Product): Product {
        val dto = ProductDto(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.imageUrl,
            rating = RatingDto(product.rating.rate, product.rating.count)
        )
        return api.updateProduct(product.id, dto).toDomain()
    }

}