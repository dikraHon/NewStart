package com.example.newstart.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.data.CartItem
import com.example.newstart.data.CartProduct
import com.example.newstart.data.Product
import com.example.newstart.reposutory.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            try {
                _products.value = RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            try {
                val cartItem = CartItem(
                    products = listOf(CartProduct(productId, 1)))
                RetrofitInstance.api.addToCart(cartItem)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun deleteFromCart(cartId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteFromCart(cartId)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                val updatedProduct = RetrofitInstance.api.updateProduct(product.id, product)
                _products.value = _products.value.map {
                    if (it.id == product.id) updatedProduct else it
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }
}