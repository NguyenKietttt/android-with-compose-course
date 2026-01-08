package com.example.flightsearch.data

import android.content.Context

class AppContainer(private val context: Context) {
    val flightSearchRepository by lazy {
        FlightSearchRepository(FlightSearchDatabase.getDatabase(context).flightSearchDao())
    }
}