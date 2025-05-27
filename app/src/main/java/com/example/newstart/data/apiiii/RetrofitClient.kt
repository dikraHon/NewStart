package com.example.newstart.data.apiiii

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.jikan.moe/"

    val instance: FakeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeApi::class.java)
    }
/*    const val URL = "https://api.jikan.moe/v4/top/anime?type=ona"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor (loggingInterceptor)
        .build()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val api: FakeApi by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FakeApi::class.java)
    }*/

}