package com.example.newstart.data

data class CartResponse(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)
