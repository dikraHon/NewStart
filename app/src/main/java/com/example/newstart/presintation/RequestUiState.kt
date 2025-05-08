package com.example.newstart.presintation

sealed class RequestUiState {
    object Idle : RequestUiState()
    object Loading : RequestUiState()
    data class Success(val data: String) : RequestUiState()
    data class Error(val message: String) : RequestUiState()
}