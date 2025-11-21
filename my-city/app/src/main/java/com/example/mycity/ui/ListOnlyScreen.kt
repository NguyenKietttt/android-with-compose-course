package com.example.mycity.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource

@Composable
fun ListOnlyScreen(
    navController: NavHostController,
    viewModel: MyCityViewModel,
    uiState: MyCityUiState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MyCityScreen.LIST_CATEGORY.name,
        modifier = modifier
    ) {
        composable(route = MyCityScreen.LIST_CATEGORY.name) {
            ListCategoryScreen(
                listCategory = DataSource.getListCategory(),
                onClick = { category ->
                    viewModel.navigateToListLocationScreen(category)
                    navController.navigate(MyCityScreen.LIST_LOCATION.name)
                }
            )
        }
        composable(route = MyCityScreen.LIST_LOCATION.name) {
            ListLocationScreen(
                listLocation = DataSource.getListLocationByCategory(uiState.currentCategory),
                onClick = { location ->
                    viewModel.navigateToLocationScreen(location)
                    navController.navigate(MyCityScreen.LOCATION.name)
                }
            )
        }
        composable(route = MyCityScreen.LOCATION.name) {
            LocationScreen(location = uiState.currentLocation)
        }
    }
}

@Preview(device = "id:pixel_8a", showSystemUi = true, showBackground = false)
@Composable
fun ListOnlyScreenPreview()
{
    val navController: NavHostController = rememberNavController()
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        ListOnlyScreen(
            navController = navController,
            viewModel = viewModel,
            uiState = uiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}