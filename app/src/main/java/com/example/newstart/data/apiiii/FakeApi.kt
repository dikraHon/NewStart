package com.example.newstart.data.apiiii

import com.example.newstart.data.model.AnimeDetailsResponse
import com.example.newstart.data.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeApi {

    @GET("v4/top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("v4/anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailsResponse

}