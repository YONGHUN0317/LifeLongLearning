package com.src.data.repository

import com.src.data.datasource.local.LocalDataSource
import com.src.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocationRepository {
    override fun getLocation(): Flow<Triple<String, String, String>> = flow {
        emit(localDataSource.getLocation())
    }

    override suspend fun setLocation(location: String, latitude: Double, longitude: Double) {
        localDataSource.setLocation(location, latitude, longitude)
    }
}