package com.example.newstart.domain

interface ModelProductsRepository {
    fun addProduct (products: Products)
    fun getProducts (): List<Products>
    fun updateProducts (products: Products)
}
