package com.example.newstart.data

import com.example.newstart.domain.ModelProductsRepository
import com.example.newstart.domain.Products

class ProductRepository : ModelProductsRepository {

    private val products = mutableListOf<Entity>()

    override fun addProduct (product: Products) {
        var id = 1
        products.add( product.toEntity().copy(id = id++) )
    }

    override fun getProducts(): List<Products> = products.map { it.toProducts() }

    override fun updateProducts (product: Products) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            products[index] = product.toEntity()
        }
    }
}