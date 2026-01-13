package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class FlightSearchViewModel(
private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {
    val searchKeyword = MutableStateFlow("")
    val isSearchBarExpanded = MutableStateFlow(false)
    val airports: StateFlow<List<Airport>> = searchKeyword
        .debounce(300L)
        .distinctUntilChanged()
        .flatMapLatest { keyword -> flightSearchRepository.findAirport(keyword) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )
    val selectedAirport = MutableStateFlow<Airport?>(null)

    fun updateSearchKeyword(newSearchKeyword: String) {
        searchKeyword.value = newSearchKeyword
    }

    fun updateSearchBarExpandStatus(isExpanded: Boolean) {
        isSearchBarExpanded.value = isExpanded
    }

    fun updateSelectedAirport(airport: Airport?) {
        selectedAirport.value = airport
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val flightSearchRepository = application.container.flightSearchRepository
                FlightSearchViewModel(flightSearchRepository)
            }
        }
    }
}