package com.example.newstart.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstart.data.ProductRepository
import com.example.newstart.domain.ModelProductsRepository
import com.example.newstart.domain.Products
import com.example.newstart.domain.useCase.AddUseCase
import com.example.newstart.domain.useCase.GetUseCase
import com.example.newstart.domain.useCase.UpdateUseCase

class ProductViewModel : ViewModel() {

    private val repository: ModelProductsRepository = ProductRepository()

    private val addUseCase = AddUseCase(repository)
    private val updateUseCase = UpdateUseCase(repository)
    private val getUseCase = GetUseCase(repository)

    private val _items = MutableLiveData<List<Products>>().apply {
        value = emptyList()
    }
    val items: LiveData<List<Products>> = _items

    fun addItem(name: String) {
        if (name.isNotBlank()) {
            addUseCase( Products(
                0,
                name,
                false
            )
            )
            _items.value = getUseCase()
        }
    }

    fun updateItem(item: Products) {
        updateUseCase(item)
        _items.value = getUseCase()
    }
}