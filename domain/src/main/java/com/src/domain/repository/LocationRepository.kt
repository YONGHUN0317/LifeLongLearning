package com.src.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(): Flow<Triple<String, String, String>>
    suspend fun setLocation(location: String, latitude: Double, longitude: Double)
}