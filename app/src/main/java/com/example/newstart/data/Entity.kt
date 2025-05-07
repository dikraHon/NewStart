package com.example.newstart.data

import com.example.newstart.domain.Products

data class Entity (
    val id: Int,
    val text: String,
    val stateEn: Boolean
) {

    fun toProducts() = Products (id, text, stateEn)

}