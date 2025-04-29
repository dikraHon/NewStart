package com.example.newstart.authorAdapter.data

sealed class Item {
    abstract val id: Long

    data class Author(
        override val id: Long,
        val name: String,
        val text: String
    ): Item()

    data class Image(
        override val id: Long,
        val urlImage: Int,
        val text: String
    ): Item()

    data class TextWithButton(
        override val id: Long,
        val text: String
    ): Item()

}