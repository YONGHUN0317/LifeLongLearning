package com.src.data.repository

import com.src.domain.repository.InterestRepository
import kotlinx.coroutines.flow.Flow
import com.src.data.datasource.local.LocalDataSource
import javax.inject.Inject

class InterestRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : InterestRepository {
    override suspend fun getSelectedInterests(): Flow<Set<String>> = localDataSource.getSelectedInterests()

    override suspend fun setSelectedInterests(selected: Set<String>) = localDataSource.setSelectedInterests(selected)
}