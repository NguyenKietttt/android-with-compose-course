package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport

@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)
) {
    val searchKeyword by viewModel.searchKeyword.collectAsState()
    val isSearchBarExpanded by viewModel.isSearchBarExpanded.collectAsState()
    val airports by viewModel.airports.collectAsState()
    val selectedAirport by viewModel.selectedAirport.collectAsState()
    val searchedAirports by viewModel.searchResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column {
            SearchBar(
                airports = airports,
                query = searchKeyword,
                onQueryChange = { viewModel.updateSearchKeyword(it) },
                expanded = isSearchBarExpanded,
                onExpandedChange = { viewModel.updateSearchBarExpandStatus(it) },
                onCloseClick = {
                    if (searchKeyword.isEmpty())
                        viewModel.updateSearchBarExpandStatus(false)
                    else
                        viewModel.updateSearchKeyword("")
                },
                onItemClick = {
                    viewModel.updateSelectedAirport(it)
                    viewModel.updateSearchBarExpandStatus(false)
                },
                modifier = Modifier.padding(innerPadding),
            )
            ListSearchResult(
                departureAirport = selectedAirport,
                destinationAirports = searchedAirports
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    airports: List<Airport>,
    query: String,
    onQueryChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    onItemClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { },
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            modifier = Modifier.clickable { onCloseClick() }
                        )
                    }
                }
            )
        },
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(airports) { airport ->
                AirportInfo(
                    airport = airport,
                    onClick = { onItemClick(airport) },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ListSearchResult(
    departureAirport: Airport?,
    destinationAirports: List<Airport>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(destinationAirports) { destinationAirport ->
            SearchResult(
                departureAirport = departureAirport,
                destinationAirport = destinationAirport,
                isFavourite = false
            )
        }
    }
}

@Composable
fun SearchResult(
    departureAirport: Airport?,
    destinationAirport: Airport,
    isFavourite: Boolean,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                Text(
                    text = "DEPART",
                    style = MaterialTheme.typography.bodySmall
                )
                AirportInfo(
                    airport = departureAirport,
                    onClick = { }
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "ARRIVE",
                    style = MaterialTheme.typography.bodySmall
                )
                AirportInfo(
                    airport = destinationAirport,
                    onClick = { }
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favourite"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultPreview() {
    val departureAirport = Airport(
        id = 0,
        iataCode = "VIE",
        fullName = "Vienna International Airport",
        numOfPassengersPerYear = 200
    )

    val destinationAirportAirport = Airport(
        id = 1,
        iataCode = "ABC",
        fullName = "ABC International Airport",
        numOfPassengersPerYear = 500
    )

    SearchResult(
        departureAirport = departureAirport,
        destinationAirport = destinationAirportAirport,
        isFavourite = false
    )
}

@Composable
fun AirportInfo(
    airport: Airport?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = airport?.iataCode ?: "",
            style = MaterialTheme.typography.titleSmall,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = airport?.fullName ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AirportInfoPreview() {
    val airport = Airport(
        id = 0,
        iataCode = "VIE",
        fullName = "Vienna International Airport",
        numOfPassengersPerYear = 200
    )
    AirportInfo(
        airport = airport,
        onClick = { },
        modifier = Modifier.padding(8.dp)
    )
}