package com.src.data.di

import com.src.data.datasource.remote.LectureApiService
import com.src.data.model.LectureData
import com.src.data.repository.LectureRepositoryImpl
import com.src.domain.repository.LectureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LectureRepositoryModule {

    @Provides
    fun provideLectureRepository(lectureApiService: LectureApiService): LectureRepository =
        LectureRepositoryImpl(lectureApiService)
}