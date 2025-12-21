package com.example.bookshelf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.screen.ErrorScreen
import com.example.bookshelf.ui.screen.LoadingScreen
import com.example.bookshelf.ui.screen.VolumeInfoScreen
import com.example.bookshelf.ui.screen.VolumeItemList
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.ui.utils.BookshelfScreen
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                val navController: NavHostController = rememberNavController()
                val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)

                Scaffold(
                    topBar = {
                        BookshelfTopAppBar(
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (viewModel.bookshelfUiState) {
                        is BookshelfUiState.Loading -> LoadingScreen(
                            Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )

                        is BookshelfUiState.Success -> {
                            NavHost(
                                navController = navController,
                                startDestination = BookshelfScreen.LIST.name
                            ) {
                                composable(route = BookshelfScreen.LIST.name) {
                                    VolumeItemList(
                                        listVolumeItem = (viewModel.bookshelfUiState as BookshelfUiState.Success).booksResponse.items!!,
                                        onClick = { volumeInfo ->
                                            Log.i("Volume", volumeInfo.toString())
                                            viewModel.setCurrentSelectedVolumeInfo(volumeInfo)
                                            navController.navigate(BookshelfScreen.DETAILS.name)
                                        },
                                        Modifier
                                            .padding(innerPadding)
                                            .fillMaxSize()
                                    )
                                }
                                composable(route = BookshelfScreen.DETAILS.name) {
                                    VolumeInfoScreen(
                                        volumeInfo = viewModel.volumeInfoState.collectAsState().value,
                                        modifier = Modifier
                                            .padding(innerPadding)
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }

                        is BookshelfUiState.Error -> ErrorScreen(
                            retryAction = { viewModel.getBookResponse("jazz+history") },
                            Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        modifier = modifier
    )
}