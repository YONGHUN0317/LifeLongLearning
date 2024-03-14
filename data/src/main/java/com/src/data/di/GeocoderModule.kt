package com.src.data.di

import android.content.Context
import android.location.Geocoder

import com.src.data.repository.GeocodingRepositoryImpl


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object GeocoderModule {
    @Provides
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }

    @Provides
    fun provideGeocodingRepository(geocoder: Geocoder): GeocodingRepositoryImpl {
        return GeocodingRepositoryImpl(geocoder)
    }
}
