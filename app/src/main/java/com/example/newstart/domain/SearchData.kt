package com.example.newstart.domain

sealed class SearchData {

    data class Success(val results: List<String>) : SearchData() {}

    data class Error(val message: String) : SearchData() {}

    object Idle : SearchData()

    object Empty : SearchData()

    object Loading : SearchData()

}