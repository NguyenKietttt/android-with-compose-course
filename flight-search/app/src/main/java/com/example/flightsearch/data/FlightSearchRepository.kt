package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FlightSearchRepository(private val flightSearchDao: FlightSearchDao) {
    fun findAirport(keyword: String): Flow<List<Airport>> {
        return flightSearchDao.findAirport(keyword)
    }

    fun getListDestinationAirport(iataCode: String): Flow<List<Airport>> {
        return if (iataCode.isEmpty())
            flowOf(emptyList())
        else
            flightSearchDao.getListDestinationAirport(iataCode)
    }

    suspend fun isFavoriteRouteExist(
        departureIataCode: String,
        destinationIataCode: String
    ): Boolean {
        return flightSearchDao.isFavoriteRouteExist(departureIataCode, destinationIataCode)
    }

    suspend fun insertFavoriteRoute(route: FavoriteRoute) {
        flightSearchDao.insertFavoriteRoute(route)
    }

    suspend fun deleteFavoriteRoute(route: FavoriteRoute) {
        flightSearchDao.deleteFavoriteRoute(route)
    }
}