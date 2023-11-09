package com.src.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object PreferencesKeys {
    val FIRST_LAUNCH = booleanPreferencesKey("first_launch")
}

class PreferencesManager(private val dataStore: DataStore<Preferences>) {

    val isFirstLaunch: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.FIRST_LAUNCH] ?: true
        }

    suspend fun updateFirstLaunch(firstLaunch: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.FIRST_LAUNCH] = firstLaunch
        }
    }
}