package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private val Context.dataStore by preferencesDataStore("search_keyword_preferences")

class AppContainer(private val context: Context) {
    val flightSearchRepository by lazy {
        FlightSearchRepository(FlightSearchDatabase.getDatabase(context).flightSearchDao())
    }

    val searchKeywordRepository by lazy {
        SearchKeywordRepository(context.dataStore)
    }
}