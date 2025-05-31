package com.example.newstart.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.domain.model.CartItem
import com.example.newstart.domain.model.Product
import com.example.newstart.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }

    fun loadProductDetails(id: Int) {
        viewModelScope.launch {
            _selectedProduct.value = repository.getProductDetails(id)
        }
    }

    fun addProductToCart(productId: Int) {
        viewModelScope.launch {
            repository.addToCart(CartItem(productId, 1))
        }
    }

    fun updateProductDetails(product: Product) {
        viewModelScope.launch {
            _selectedProduct.value = repository.updateProduct(product)
            loadProducts()
        }
    }
}