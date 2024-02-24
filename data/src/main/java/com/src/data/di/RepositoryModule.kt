package com.src.data.di

import com.src.data.db.LectureDao
import com.src.data.repository.GeocodingRepositoryImpl
import com.src.data.repository.InterestRepositoryImpl
import com.src.data.repository.LectureRepositoryImpl
import com.src.data.repository.LocationRepositoryImpl
import com.src.data.repository.PreferencesRepositoryImpl
import com.src.domain.repository.GeocodingRepository
import com.src.domain.repository.InterestRepository
import com.src.domain.repository.LectureRepository
import com.src.domain.repository.LocationRepository
import com.src.domain.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindInterestRepository(impl: InterestRepositoryImpl): InterestRepository

    @Binds
    abstract fun bindPreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindGeocodingRepository(impl: GeocodingRepositoryImpl): GeocodingRepository

}

