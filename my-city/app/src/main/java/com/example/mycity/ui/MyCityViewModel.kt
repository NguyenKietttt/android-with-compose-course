package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    fun navigateToListCategoryScreen() {
        _uiState.update {
            it.copy(
                isShowingListLocationScreen = false,
                isShowingLocationScreen = false,
            )
        }
    }

    fun navigateToListLocationScreen(category: String) {
        _uiState.update {
            it.copy(
                currentCategory = category,
                isShowingListLocationScreen = true,
                isShowingLocationScreen = false,
            )
        }
    }

    fun navigateToLocationScreen(location: Location) {
        _uiState.update {
            it.copy(
                currentLocation = location,
                isShowingListLocationScreen = false,
                isShowingLocationScreen = true,
            )
        }
    }
}