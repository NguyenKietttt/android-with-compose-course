package com.example.flightsearch.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchKeywordRepository(private val dataStore: DataStore<Preferences>) {
    val searchKeyword: Flow<String> = dataStore.data.map { preferences ->
        preferences[SEARCH_KEYWORD] ?: ""
    }

    suspend fun saveSearchKeyword(keyword: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_KEYWORD] = keyword
        }
    }

    private companion object {
        val SEARCH_KEYWORD = stringPreferencesKey("search_keyword")
    }
}