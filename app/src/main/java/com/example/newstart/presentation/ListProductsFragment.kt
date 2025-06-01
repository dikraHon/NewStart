package com.example.newstart.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstart.adapter.ProductAdapter
import com.example.newstart.databinding.FragmentListProductsBinding
import androidx.fragment.app.viewModels

class ListProductsFragment : Fragment() {

    private var _binding: FragmentListProductsBinding? = null
    private val binding get() = _binding
    private var adapter: ProductAdapter? = null
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListProductsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupAddButton()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter { updatedItem ->
            viewModel.updateItem(updatedItem)
        }
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapter

    }

    private fun setupAddButton() {
        binding?.addItemButton?.setOnClickListener {
            val itemName = binding?.landEnterEditText?.text.toString()
            viewModel.addItem(itemName)
            binding?.landEnterEditText?.text?.clear()
        }
    }

    private fun observeViewModel() {
        viewModel.items.observe(viewLifecycleOwner) { products ->
            adapter?.submitList(products)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}