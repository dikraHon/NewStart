package com.example.newstart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.authorAdapter.data.Item
import com.example.newstart.authorAdapter.RecyclerViewAdapter
import com.example.newstart.authorAdapter.VerticalSpaceItemDecoration
import com.example.newstart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding is null")
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        recyclerView = binding.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter()
        recyclerView?.adapter = adapter
        setupSpace()

        listItems()
        binding.refreshButton.setOnClickListener {
            refreshData()
        }

    }

    private fun setupSpace() {
        val spacePx = (20 * resources.displayMetrics.density).toInt()

        recyclerView?.addItemDecoration(
            VerticalSpaceItemDecoration(
                spaceHeight = spacePx,
                excludeFirst = true,
                excludeLast = true
            )
        )

        recyclerView?.clipToPadding = false
    }

    private fun listItems() {
        val items = mutableListOf<Item>()

        items.add(Item.Author(1, "Android 1", "hi, my name and1"))
        items.add(Item.Image(2, R.drawable.and, "this just image"))
        items.add(Item.TextWithButton(3, "I am text"))
        items.add(Item.Author(4, "Android 4", "and my name and2"))

        adapter?.updateItems(items)
    }

    private fun refreshData() {
        val newItems = mutableListOf<Item>()

        newItems.add(Item.Author(5, "New author", "this new"))
        newItems.add(Item.Image(6, R.drawable.and2, "new image"))
        newItems.add(Item.TextWithButton(7, "new text"))
        newItems.add(Item.Author(8, "New author22", "very new image"))
        newItems.add(Item.Image(9, R.drawable.and3, "i am new image"))

        adapter?.updateItems(newItems)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}