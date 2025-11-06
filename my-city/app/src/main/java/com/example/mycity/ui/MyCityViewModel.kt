package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    fun navigateToCategoryPage() {
        _uiState.update {
            it.copy(
                isShowingLocationPage = false,
                isShowingDetailsPage = false,
            )
        }
    }

    fun navigateToLocationPage() {
        _uiState.update {
            it.copy(
                isShowingLocationPage = true,
                isShowingDetailsPage = false,
            )
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(
                isShowingLocationPage = false,
                isShowingDetailsPage = true,
            )
        }
    }
}