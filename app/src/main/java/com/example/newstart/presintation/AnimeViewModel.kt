package com.example.newstart.presintation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.data.apiiii.RetrofitClient
import com.example.newstart.data.model.Anime
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {

    private val _animeList = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> = _animeList

    private val _selectedAnime = MutableLiveData<Anime?>()
    val selectedAnime: LiveData<Anime?> = _selectedAnime

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun fetchTopAnime() {

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getTopAnime()
                _animeList.value = response.data
            } catch (e: Exception) {
                e.message
            } finally {
                _isLoading.value = false
            }
        }

    }

    fun fetchAnimeDetails(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getAnimeDetails(id)
                _selectedAnime.value = response.data
            } catch (e: Exception) {
                e.message
            } finally {
                _isLoading.value = false
            }
        }
    }


}