package com.example.flightsearch.data

import androidx.room.Embedded

data class AirportFavorite (
    @Embedded val airport: Airport,
    val isFavorite: Boolean
)