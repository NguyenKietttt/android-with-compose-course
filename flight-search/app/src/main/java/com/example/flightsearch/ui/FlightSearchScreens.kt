package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport

@Composable
fun FlightSearchApp(viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)) {

    val searchKeyword by viewModel.searchKeyword.collectAsState()
    val isSearchBarExpanded by viewModel.isSearchBarExpanded.collectAsState()
    val airports by viewModel.airports.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SearchScreen(
            airports = airports,
            query = searchKeyword,
            onQueryChange = { viewModel.updateSearchKeyword(it) },
            onSearch = {  },
            expanded = isSearchBarExpanded,
            onExpandedChange =  { viewModel.updateSearchBarExpandStatus(it) },
            onCloseClick = {
                if(searchKeyword.isEmpty())
                    viewModel.updateSearchBarExpandStatus(false)
                else
                    viewModel.updateSearchKeyword("")
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    airports: List<Airport>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                placeholder = { Text("Search") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
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
                SearchSuggestion(
                    airport = airport,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun SearchSuggestion(
    airport: Airport,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = airport.iataCode,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(text = airport.fullName)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchSuggestionPreview() {
    val previewAirport = Airport(id = 0, iataCode = "VIE", fullName = "Vienna International Airport", numOfPassengersPerYear = 200)
    SearchSuggestion(airport = previewAirport, onClick = { })
}