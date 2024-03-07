/*
package com.src.data.repository

import com.src.data.datasource.local.LocalDataSource
import javax.inject.Inject

class LocationRepository  @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    suspend fun getLocation(): Triple<String, String, String> {
        return localDataSource.getLocation()
    }

    suspend fun setLocation(location: String, latitude: String, longitude: String) {
        localDataSource.setLocation(location, latitude, longitude)
    }
}*/
