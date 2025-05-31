package com.example.newstart.presintation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.newstart.adapter.ProductAdapter
import com.example.newstart.databinding.ActivityMainBinding
import com.example.newstart.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupRecyclerView()
        setupObservers()
        viewModel.loadProducts()

    }

    private fun setupRecyclerView() {

        adapter = ProductAdapter(
            onItemClick = { product ->
                viewModel.loadProductDetails(product.id)
            },
            onAddToCart = { product ->
                viewModel.addProductToCart(product.id)
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
            }
        )
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.products.collect { products ->
                adapter.submitList(products)
            }
        }

        lifecycleScope.launch {
            viewModel.selectedProduct.collect { product ->
                product?.let { updateProductDetailsView(it) }
            }
        }
    }



    private fun updateProductDetailsView(product: Product) {
        binding?.productImage?.load(product.imageUrl)
        binding?.productTitle?.text = product.title
        binding?.productPrice?.text = "$${product.price}"
        binding?.productDescription?.text = product.description

        binding?.updateButton?.setOnClickListener {
            val updatedProduct = product.copy(
                title = binding?.productTitle?.text.toString(),
                price = binding?.productPrice?.text.toString().removePrefix("$").toDouble(),
                description = binding?.productDescription?.text.toString()
            )
            viewModel.updateProductDetails(updatedProduct)
        }
    }

}