package com.src.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface LocalDataSource  {
    suspend fun getSelectedInterests(): Flow<Set<String>>
    suspend fun setSelectedInterests(interests: Set<String>)

    suspend fun getLocation(): String
    suspend fun setLocation(location: String)
}

