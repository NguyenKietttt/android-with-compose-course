package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favourite")
data class FavoriteRoute(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "departure_code") val departureIataCode: String,
    @ColumnInfo(name = "destination_code") val destinationIataCode: String,
)
