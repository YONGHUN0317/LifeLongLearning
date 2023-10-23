package com.src.data.repository

import com.src.data.datasource.local.LocalDataSource
import javax.inject.Inject

class InterestRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    suspend fun getSelectedInterests(): Set<String> {
        return localDataSource.getSelectedInterests()
    }

    suspend fun setSelectedInterests(selected: Set<String>) {
        localDataSource.setSelectedInterests(selected)
    }
}

