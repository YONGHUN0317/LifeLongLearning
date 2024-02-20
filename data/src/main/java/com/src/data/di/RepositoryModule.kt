package com.src.data.di

import com.src.data.repository.InterestRepositoryImpl
import com.src.data.repository.PreferencesRepositoryImpl
import com.src.domain.repository.InterestRepository
import com.src.domain.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindInterestRepository(impl: InterestRepositoryImpl): InterestRepository

    @Binds
    abstract fun bindPreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository
}
  /*  @Provides
    fun provideLectureRepository(lectureDao: LectureDao): LectureRepository =
        LectureRepositoryImpl(lectureDao)*/
