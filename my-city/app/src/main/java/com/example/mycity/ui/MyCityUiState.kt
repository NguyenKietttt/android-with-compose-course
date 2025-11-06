package com.example.mycity.ui

import com.example.mycity.data.DataSource
import com.example.mycity.model.Location

data class MyCityUiState(
    val currentCategory: String = DataSource.getDefaultCategory(),
    val currentLocation: Location = DataSource.getDefaultLocation(),
    val isShowingLocationPage: Boolean = false,
    val isShowingDetailsPage: Boolean = false
)
