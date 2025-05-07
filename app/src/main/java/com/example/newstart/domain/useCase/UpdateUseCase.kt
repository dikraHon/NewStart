package com.example.newstart.domain.useCase

import com.example.newstart.domain.ModelProductsRepository
import com.example.newstart.domain.Products

class UpdateUseCase (private val repository: ModelProductsRepository) {

    operator fun invoke (products: Products) = repository.updateProducts(products)

}