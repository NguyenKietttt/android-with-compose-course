package com.example.mycity.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource

@Composable
fun MyCityApp() {
    val navController: NavHostController = rememberNavController()
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.LIST_CATEGORY.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.LIST_CATEGORY.name) {
                ListCategoryScreen(
                    listCategory = DataSource.getListCategory(),
                    onClick = { },
                    modifier = Modifier
                )
            }
        }
    }
}