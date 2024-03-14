package com.src.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource  {
    suspend fun getSelectedInterests(): Flow<Set<String>>
    suspend fun setSelectedInterests(interests: Set<String>)

    suspend fun getLocation(): Triple<String, String, String>
    suspend fun setLocation(location: String, latitude: Double, longitude: Double)
}

