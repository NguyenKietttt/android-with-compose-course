package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("airport")
data class Airport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "iata_code") val iataCode: String,
    @ColumnInfo(name = "name") val fullName: String,
    @ColumnInfo(name = "passengers") val numOfPassengersPerYear: Int
)