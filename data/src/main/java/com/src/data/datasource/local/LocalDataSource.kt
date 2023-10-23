package com.src.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first

interface LocalDataSource {
    suspend fun getSelectedInterests(): Set<String>
    suspend fun setSelectedInterests(interests: Set<String>)
}

class LocalDataSourceImpl(private val dataStore: DataStore<Preferences>) : LocalDataSource {
    private val selectedInterestsKey = stringSetPreferencesKey("selected_interests")

    override suspend fun getSelectedInterests(): Set<String> {
        val preferences = dataStore.data.first()
        return preferences[selectedInterestsKey] ?: setOf()
    }

    override suspend fun setSelectedInterests(interests: Set<String>) {
        dataStore.edit { preferences ->
            preferences[selectedInterestsKey] = interests
        }
    }
}
