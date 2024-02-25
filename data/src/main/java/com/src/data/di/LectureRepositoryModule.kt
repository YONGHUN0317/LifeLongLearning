package com.src.data.di

import com.src.data.db.LectureDao
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
    fun provideLectureRepository(lectureDao: LectureDao): LectureRepository =
        LectureRepositoryImpl(lectureDao)
}