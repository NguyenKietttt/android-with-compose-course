package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class FlightSearchRepository(private val flightSearchDao: FlightSearchDao) {
    fun findAirport(keyword: String): Flow<List<Airport>> {
        return flightSearchDao.findAirport(keyword)
    }

    fun getListDestinationAirport(iataCode: Int): Flow<List<Airport>> {
        return flightSearchDao.getListDestinationAirport(iataCode)
    }
}