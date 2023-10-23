package com.src.data.di

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import com.src.data.datasource.local.LocalDataSource
import com.src.data.datasource.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        dataStore: DataStore<Preferences>
    ): LocalDataSource {
        return LocalDataSourceImpl(dataStore)
    }
}
