package com.example.newstart.presintation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.presintation.ProductViewModel
import com.example.newstart.adapter.ProductAdapter
import com.example.newstart.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.loadProducts()
    }
    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            onAddToCart = { productId -> viewModel.addToCart(productId) },
            onEdit = { product ->
                val updatedProduct = product.copy(price = 999.99)
                viewModel.updateProduct(updatedProduct)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.products.collect { products ->
                adapter.submitList(products)
            }
        }

    }
}