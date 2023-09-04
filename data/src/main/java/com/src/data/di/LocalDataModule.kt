package com.src.data.di

import android.content.Context
import androidx.room.Room
import com.src.data.model.LectureInfo
import com.src.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "lecture_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLectureInfoDao(database: AppDatabase): LectureInfo {
        return database.lectureInfoDao()
    }
}