package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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
    fun getListDestinationAirport(iataCode: String): Flow<List<Airport>>

    @Query("SELECT * " +
                    "FROM favorite " +
                    "WHERE departure_code == :departureIataCode " +
                    "AND destination_code == :destinationIataCode")
    fun getFavoriteRoute(
        departureIataCode: String,
        destinationIataCode: String
    ): Flow<List<FavoriteRoute>>

    @Insert
    suspend fun insertFavoriteRoute(favoriteRoute: FavoriteRoute)

    @Delete
    suspend fun deleteFavoriteRoute(favoriteRoute: FavoriteRoute)
}