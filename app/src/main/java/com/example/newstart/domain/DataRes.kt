package com.example.newstart.domain

sealed class DataRes {
    data class Success (val data: String) : DataRes()
    data class Error (val message: String) : DataRes()
}