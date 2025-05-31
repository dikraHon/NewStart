package com.example.newstart.data.dto

import com.example.newstart.domain.model.Rating

data class RatingDto(
    val rate: Double,
    val count: Int
) {
    fun toDomain() = Rating(rate, count)
}