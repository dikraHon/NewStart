package com.example.newstart.domain

import com.example.newstart.data.Entity

data class Products (
    val id: Int,
    val name: String,
    val state: Boolean
) {

    fun toEntity() = Entity(
        id = id,
        text = name,
        stateEn = state)

}