package com.example.newstart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstart.adapter.RecyclerViewAdapter
import com.example.newstart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding is null")
    private val viewModel: CountViewModel by viewModels()
    private var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView() {
        viewModel.count.observe(this) {
            count -> binding.countTextView.text = count.toString()
        }
        adapter = RecyclerViewAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.count.observe(this) {
        }
    }

    override fun onCreateDescription(): CharSequence? {
        return super.onCreateDescription()
        _binding = null
    }
}