package com.src.data.di

import com.src.data.api.GooglePlacesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideGooglePlacesApi(): GooglePlacesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(GooglePlacesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GooglePlacesApi::class.java)
    }
}