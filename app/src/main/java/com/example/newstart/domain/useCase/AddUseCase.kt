package com.example.newstart.domain.useCase

import com.example.newstart.domain.ModelProductsRepository
import com.example.newstart.domain.Products
import org.w3c.dom.Text

class AddUseCase (private val repository: ModelProductsRepository) {

    operator fun invoke (products: Products) = repository.addProduct(products)

}