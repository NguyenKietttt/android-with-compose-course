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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport

@Composable
fun FlightSearchApp(viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SearchScreen(
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: FlightSearchViewModel,
    modifier: Modifier = Modifier
) {
    var keyword by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = keyword,
                onQueryChange = {
                    keyword = it
                },
                onSearch = { isExpanded = false },
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it },
                placeholder = { Text("Search") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
                trailingIcon = {
                    if (isExpanded) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            modifier = Modifier.clickable {
                                if (keyword.isEmpty()) isExpanded = false else keyword = ""
                            })
                    }
                }
            )
        },
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        val airports by viewModel.findAirport(keyword).collectAsState(emptyList())
        LazyColumn {
            items(airports) { airport ->
                SearchItem(
                    airport = airport,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun SearchItem(
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
fun SearchItemPreview() {
    val previewAirport = Airport(id = 0, iataCode = "VIE", fullName = "Vienna International Airport", numOfPassengersPerYear = 200)
    SearchItem(airport = previewAirport, onClick = { })
}