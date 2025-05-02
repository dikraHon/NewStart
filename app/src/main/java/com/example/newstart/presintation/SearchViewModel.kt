package com.example.newstart.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.data.SearchRepositoryImpl
import com.example.newstart.domain.SearchData
import com.example.newstart.domain.SearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = SearchRepositoryImpl()
    private val searchUseCase: SearchUseCase = SearchUseCase ( repository )
    private val _state = MutableStateFlow<SearchData> ( SearchData.Idle )
    private var searchJob : Job? = null
    val state: StateFlow<SearchData> = _state.asStateFlow()

    fun search ( query : String ) {

        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            _state.value = SearchData.Loading

            try {

                val results = searchUseCase( query )
                _state.value = if ( results.isEmpty() ) {
                    SearchData.Empty
                } else {
                    SearchData.Success ( results )
                }

            } catch ( e: Exception ) {
                _state.value = SearchData.Error ( e.message ?: "Error" )
            }

        }

    }

}