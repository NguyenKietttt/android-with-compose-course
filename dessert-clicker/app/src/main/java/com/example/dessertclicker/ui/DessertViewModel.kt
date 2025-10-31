package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    public val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    public fun onDessertClicked() {
        _uiState.update {
            val dessertSold = it.dessertsSold + 1
            val nextDessertIndex = determineDessertToShow(dessertSold)
            it.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = it.revenue + it.currentDessertPrice,
                dessertsSold = dessertSold,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
                currentDessertPrice = dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertToShow(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertIndex
    }
}