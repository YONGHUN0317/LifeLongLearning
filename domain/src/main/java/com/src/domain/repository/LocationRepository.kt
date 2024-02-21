package com.src.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(): Flow<String>
    suspend fun setLocation(location: String)
}