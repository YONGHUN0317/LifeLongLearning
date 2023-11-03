package com.src.data.repository

import com.src.data.datasource.local.LocalDataSource
import javax.inject.Inject

class LocationRepository  @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    suspend fun getLocation(): String {
        return localDataSource.getLocation()
    }

    suspend fun setLocation(location: String) {
        localDataSource.setLocation(location)
    }
}