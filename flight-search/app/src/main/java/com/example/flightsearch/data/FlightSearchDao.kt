package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {
    @Query("SELECT * FROM airport WHERE iata_code LIKE '%' || :keyword || '%' OR name LIKE '%' || :keyword || '%' ORDER BY passengers DESC")
    fun findAirport(keyword: String): Flow<List<Airport>>

    @Query("""
        SELECT 
            -- If we found a favorite record, set to 1 (true), else 0 (false)
            CASE WHEN f.id IS NOT NULL THEN 1 ELSE 0 END AS isFavorite,
            
            -- Departure Airport (The code you entered)
            a1.id AS dep_id, a1.iata_code AS dep_iata_code, 
            a1.name AS dep_name, a1.passengers AS dep_passengers,
            
            -- Destination Airport (All other airports)
            a2.id AS dest_id, a2.iata_code AS dest_iata_code, 
            a2.name AS dest_name, a2.passengers AS dest_passengers
            
        FROM airport a2
        CROSS JOIN airport a1 ON a1.iata_code = :departureIataCode
        LEFT JOIN favorite f ON (f.departure_code = a1.iata_code AND f.destination_code = a2.iata_code)
        WHERE a2.iata_code != :departureIataCode
    """)
    fun getRoutes(departureIataCode: String): Flow<List<Route>>

    @Query("""
        SELECT 
            1 AS isFavorite, -- Hardcoded to 1 (true) since we only fetch favorites
            
            -- Departure Airport Details (Joined on departure_code)
            a1.id AS dep_id, a1.iata_code AS dep_iata_code, 
            a1.name AS dep_name, a1.passengers AS dep_passengers,
            
            -- Destination Airport Details (Joined on destination_code)
            a2.id AS dest_id, a2.iata_code AS dest_iata_code, 
            a2.name AS dest_name, a2.passengers AS dest_passengers
            
        FROM favorite f
        INNER JOIN airport a1 ON f.departure_code = a1.iata_code
        INNER JOIN airport a2 ON f.destination_code = a2.iata_code
    """)
    fun getFavoriteRoutes(): Flow<List<Route>>

    @Insert
    suspend fun insertFavoriteRoute(favoriteRoute: FavoriteRoute)

    @Query("DELETE FROM favorite WHERE departure_code = :departureIataCode AND destination_code = :destinationIataCode")
    suspend fun deleteFavoriteRoute(departureIataCode: String, destinationIataCode: String)
}