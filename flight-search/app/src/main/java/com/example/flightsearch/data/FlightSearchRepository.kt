package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class FlightSearchRepository(private val flightSearchDao: FlightSearchDao) {
    fun findAirport(keyword: String): Flow<List<Airport>> = flightSearchDao.findAirport(keyword)
}