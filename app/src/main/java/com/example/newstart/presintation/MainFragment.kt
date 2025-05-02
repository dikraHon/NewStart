package com.example.newstart.presintation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newstart.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        binding?.loadButton?.setOnClickListener {
            viewModel.loadData()
        }

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is RequestUiState.Idle -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.resultTextView?.text = ""
                    }
                    is RequestUiState.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.resultTextView?.text = ""
                    }
                    is RequestUiState.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.resultTextView?.text = state.data
                    }
                    is RequestUiState.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.resultTextView?.text = "Error: ${state.message}"
                    }
                }
            }
        }

    }

}