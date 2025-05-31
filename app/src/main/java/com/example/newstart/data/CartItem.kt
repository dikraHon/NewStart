package com.example.newstart.data

import com.squareup.moshi.Json

data class CartItem(
    val userId: Int = 1,
    val date: String = "2025-05-05",
    val products: List<CartProduct>
)