package com.src.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.src.data.datasource.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(private val dataStore: DataStore<Preferences>) : LocalDataSource {
    private val selectedInterestsKey = stringSetPreferencesKey("selected_interests")
    private val userLocationKey = stringPreferencesKey("user_location")

    override suspend fun getSelectedInterests(): Flow<Set<String>> {
        return flow {
            val preferences = dataStore.data.first()
            val selectedInterests = preferences[selectedInterestsKey] ?: setOf()
            emit(selectedInterests)
        }
    }

    override suspend fun setSelectedInterests(interests: Set<String>) {
        dataStore.edit { preferences ->
            preferences[selectedInterestsKey] = interests
        }
    }

    override suspend fun getLocation(): Triple<String, String, String> {
        val preferences = dataStore.data.first()
        val combinedLocation = preferences[userLocationKey] ?: ""
        val parts = combinedLocation.split(",")
        return if (parts.size == 3) {
            Triple(parts[0], parts[1], parts[2])
        } else {
            Triple("", "", "")
        }
    }

    override suspend fun setLocation(location: String, latitude: Double, longitude: Double) {
        val combinedLocation = "$location,$latitude,$longitude"
        dataStore.edit { preferences ->
            preferences[userLocationKey] = combinedLocation
        }
    }



}