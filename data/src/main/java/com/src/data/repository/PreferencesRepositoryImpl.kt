package com.src.data.repository

import com.src.domain.repository.PreferencesRepository
import com.src.data.datasource.local.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : PreferencesRepository {
    override fun isFirstLaunch(): Flow<Boolean> = preferencesManager.isFirstLaunch

    override suspend fun updateFirstLaunch(isFirstLaunch: Boolean) = preferencesManager.updateFirstLaunch(isFirstLaunch)
}