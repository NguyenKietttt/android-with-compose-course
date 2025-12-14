package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.screen.ErrorScreen
import com.example.bookshelf.ui.screen.LoadingScreen
import com.example.bookshelf.ui.screen.VolumeItemList
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
                Scaffold(
                    topBar = { BookshelfTopAppBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (viewModel.bookshelfUiState) {
                        is BookshelfUiState.Loading -> LoadingScreen(
                            Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                        is BookshelfUiState.Success -> VolumeItemList(
                            (viewModel.bookshelfUiState as BookshelfUiState.Success).booksResponse.items!!,
                            Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
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
fun BookshelfTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        modifier = modifier
    )
}