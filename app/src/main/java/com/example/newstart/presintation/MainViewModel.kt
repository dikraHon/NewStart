package com.example.newstart.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.data.Repository
import com.example.newstart.domain.DataRepository
import com.example.newstart.domain.DataRes
import com.example.newstart.domain.GetDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val repository: DataRepository = Repository()
    private val getDataUseCase =  GetDataUseCase(repository)

    private val _uiState = MutableStateFlow<RequestUiState>(RequestUiState.Idle)
    val uiState: StateFlow<RequestUiState> = _uiState

    fun loadData() {
        _uiState.value = RequestUiState.Loading

        viewModelScope.launch {
            when (
                val result = withContext(Dispatchers.IO) {
                    getDataUseCase()
                }
            ) {
                is DataRes.Success -> {
                    _uiState.value = RequestUiState.Success(result.data)
                }
                is DataRes.Error -> {
                    _uiState.value = RequestUiState.Error(result.message)
                }
            }
        }
    }
}
