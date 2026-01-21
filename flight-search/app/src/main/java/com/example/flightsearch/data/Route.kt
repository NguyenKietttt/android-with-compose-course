package com.example.flightsearch.data

import androidx.room.Embedded

data class Route(
    val isFavorite: Boolean,
    @Embedded(prefix = "dep_") val departureAirport: Airport,
    @Embedded(prefix = "dest_") val destinationAirport: Airport
)
