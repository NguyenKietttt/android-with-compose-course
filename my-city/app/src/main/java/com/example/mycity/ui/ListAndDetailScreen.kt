package com.example.mycity.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource

@Composable
fun ListAndDetailScreen(
    viewModel: MyCityViewModel,
    uiState: MyCityUiState,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ListCategoryScreen(
            listCategory = DataSource.getListCategory(),
            onClick = { category ->
                viewModel.navigateToListLocationScreen(category)
                val listLocation = DataSource.getListLocationByCategory(category)
                viewModel.navigateToLocationScreen(listLocation[0])
            },
            modifier = Modifier
                .weight(1.0f)
                .padding(start = 16.dp)
        )
        Spacer(Modifier.width(16.dp))
        ListLocationScreen(
            listLocation = DataSource.getListLocationByCategory(uiState.currentCategory),
            onClick = { location ->
                viewModel.navigateToLocationScreen(location)
            },
            modifier = Modifier.weight(1.0f)
        )
        Spacer(Modifier.width(16.dp))
        LocationScreen(
            location = uiState.currentLocation,
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 16.dp)
        )
    }
}

@Preview(device = "id:pixel_tablet", showSystemUi = true, showBackground = false)
@Composable
fun ListAndDetailScreenPreview() {
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        ListAndDetailScreen(
            viewModel = viewModel,
            uiState = uiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}