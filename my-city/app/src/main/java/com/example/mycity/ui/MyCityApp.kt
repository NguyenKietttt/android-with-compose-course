package com.example.mycity.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.data.DataSource

@Composable
fun MyCityApp() {
    val navController: NavHostController = rememberNavController()
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.LIST_CATEGORY.name
    )

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                uiState = uiState,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.LIST_CATEGORY.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.LIST_CATEGORY.name) {
                ListCategoryScreen(
                    listCategory = DataSource.getListCategory(),
                    onClick = { category ->
                        viewModel.navigateToListLocationScreen(category)
                        navController.navigate(MyCityScreen.LIST_LOCATION.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = MyCityScreen.LIST_LOCATION.name) {
                ListLocationScreen(
                    listLocation = DataSource.getListLocationByCategory(uiState.currentCategory),
                    onClick = { location ->
                        viewModel.navigateToLocationScreen(location)
                        navController.navigate(MyCityScreen.LOCATION.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = MyCityScreen.LOCATION.name) {
                LocationScreen(
                    location = uiState.currentLocation,
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityScreen,
    uiState: MyCityUiState,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = when (currentScreen) {
        MyCityScreen.LIST_CATEGORY -> stringResource(R.string.app_name)
        MyCityScreen.LIST_LOCATION -> uiState.currentCategory
        else -> uiState.currentLocation.name
    }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}