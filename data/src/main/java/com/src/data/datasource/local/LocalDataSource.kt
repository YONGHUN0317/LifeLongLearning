package com.src.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first

interface LocalDataSource  {
    suspend fun getSelectedInterests(): Set<String>
    suspend fun setSelectedInterests(interests: Set<String>)

    suspend fun getLocation(): String
    suspend fun setLocation(location: String)
}

class LocalDataSourceImpl(private val dataStore: DataStore<Preferences>) : LocalDataSource {
    private val selectedInterestsKey = stringSetPreferencesKey("selected_interests")
    private val userLocationKey = stringPreferencesKey("user_location")

    override suspend fun getSelectedInterests(): Set<String> {
        val preferences = dataStore.data.first()
        return preferences[selectedInterestsKey] ?: setOf()
    }

    override suspend fun setSelectedInterests(interests: Set<String>) {
        dataStore.edit { preferences ->
            preferences[selectedInterestsKey] = interests
        }
    }

    override suspend fun getLocation(): String {
        val preferences = dataStore.data.first()
        return preferences[userLocationKey] ?: ""
    }

    override suspend fun setLocation(location: String) {
        dataStore.edit { preferences ->
            preferences[userLocationKey] = location
        }
    }
}
