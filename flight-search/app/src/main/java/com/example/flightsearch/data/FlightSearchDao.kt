package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {
    @Query("SELECT * " +
                    "FROM airport " +
                    "WHERE iata_code LIKE '%' || :keyword || '%' " +
                    "OR name LIKE '%' || :keyword || '%' " +
                    "ORDER BY passengers DESC")
    fun findAirport(keyword: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code != :iataCode")
    fun getListDestinationAirport(iataCode: Int): Flow<List<Airport>>
}