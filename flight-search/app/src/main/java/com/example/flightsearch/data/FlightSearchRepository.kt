package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FlightSearchRepository(private val flightSearchDao: FlightSearchDao) {
    fun findAirport(keyword: String): Flow<List<Airport>> {
        return flightSearchDao.findAirport(keyword)
    }

    fun getRoutes(departureIataCode: String): Flow<List<Route>> {
        return if (departureIataCode.isEmpty())
            flowOf(emptyList())
        else
            flightSearchDao.getRoutes(departureIataCode)
    }

    fun getFavoriteRoutes(): Flow<List<Route>> {
        return flightSearchDao.getFavoriteRoutes()
    }

    suspend fun insertFavoriteRoute(route: FavoriteRoute) {
        flightSearchDao.insertFavoriteRoute(route)
    }

    suspend fun deleteFavoriteRoute(departureIataCode: String, destinationIataCode: String) {
        flightSearchDao.deleteFavoriteRoute(departureIataCode, destinationIataCode)
    }
}