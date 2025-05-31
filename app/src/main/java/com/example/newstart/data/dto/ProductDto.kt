package com.example.newstart.data.dto

import com.example.newstart.domain.model.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
) {
    fun toDomain() = Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = image,
        rating = rating.toDomain()
    )
}