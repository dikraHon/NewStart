package com.example.newstart.presintation.di

import com.example.newstart.data.api.FakeStoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }
}