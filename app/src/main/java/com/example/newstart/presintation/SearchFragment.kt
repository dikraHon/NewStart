package com.example.newstart.presintation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.adapter.SearchRecyclerAdapter
import com.example.newstart.databinding.FragmentSearchBinding
import com.example.newstart.domain.SearchData
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding? = null
    private var adapter : SearchRecyclerAdapter? = null
    private var recyclerView : RecyclerView? = null
    private val viewModel : SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observeState()

    }

    private fun setupRecyclerView() {

        adapter = SearchRecyclerAdapter()
        recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager( requireContext() )
        recyclerView?.adapter = adapter

    }

    private fun setupSearch() {

        binding?.searchEditText?.doAfterTextChanged { text ->
            text?.toString()?.takeIf { it.isNotBlank() }?.let { query ->
                viewModel.search(query)
            }
        }

    }

    private fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect { state ->

                    when ( state ) {

                        SearchData.Idle -> clearUi()
                        SearchData.Loading -> showLoading()
                        is SearchData.Success -> showResults(state.results)
                        SearchData.Empty -> showEmpty()
                        is SearchData.Error -> showError(state.message)

                    }

                }

            }

        }

    }

    private fun clearUi() {

        with ( binding!! ) {
            progressBar.gone()
            errorText.gone()
            emptyText.gone()
            recyclerView.visible()
            adapter?.submitList( emptyList() )
        }

    }

    private fun showLoading() {

        with ( binding!! ) {
            progressBar.visible()
            errorText.gone()
            emptyText.gone()
        }

    }

    private fun showResults ( results: List<String> ) {

        with ( binding!! ) {
            progressBar.gone()
            errorText.gone()
            emptyText.gone()
            adapter?.submitList( results )
        }

    }

    private fun showEmpty() {

        with ( binding!! ) {
            progressBar.gone()
            errorText.gone()
            emptyText.visible()
        }

    }

    private fun showError ( message: String ) {

        with( binding!! ) {
            progressBar.gone()
            errorText.text = message
            errorText.visible()
        }

    }

    override fun onDestroyView() {

        super.onDestroyView()

    }

    fun View.visible() { visibility = View.VISIBLE }

    fun View.gone() { visibility = View.GONE }

}