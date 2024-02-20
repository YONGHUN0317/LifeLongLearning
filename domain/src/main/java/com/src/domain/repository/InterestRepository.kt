package com.src.domain.repository

import kotlinx.coroutines.flow.Flow

interface InterestRepository {
    suspend fun getSelectedInterests(): Flow<Set<String>>
    suspend fun setSelectedInterests(selected: Set<String>)
}