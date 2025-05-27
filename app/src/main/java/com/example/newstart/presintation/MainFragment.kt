package com.example.newstart.presintation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newstart.adapter.AnimeAdapter
import com.example.newstart.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: AnimeViewModel by viewModels()
    private var adapter: AnimeAdapter? = null
    private var animeRecyclerView: RecyclerView? = null
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTopAnime()

    }

    private fun setupRecyclerView() {

        adapter = AnimeAdapter() { anime ->
            viewModel.fetchAnimeDetails(anime.id)
        }

        animeRecyclerView = binding?.animeRecyclerView
        animeRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        animeRecyclerView?.adapter = adapter

    }


    private fun observeViewModel() {

        viewModel.animeList.observe(viewLifecycleOwner) { animeList ->
            adapter?.submitList(animeList)
        }

        viewModel.selectedAnime.observe(viewLifecycleOwner) { anime ->

            anime?.let {
                binding?.detailImageView?.load(anime.images.jpg.image_url)
                binding?.detailTitleTextView?.text = anime.title
            }

        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

    }

}