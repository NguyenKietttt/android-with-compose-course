package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.AmphibiansViewModel
import com.example.amphibians.ui.screen.AmphibianList
import com.example.amphibians.ui.screen.ErrorScreen
import com.example.amphibians.ui.screen.LoadingScreen
import com.example.amphibians.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansTheme {
                val viewModel: AmphibiansViewModel =
                    viewModel(factory = AmphibiansViewModel.Factory)
                Scaffold(
                    topBar = { AmphibiansTopAppBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (viewModel.amphibiansUiState) {
                        is AmphibiansUiState.Loading -> LoadingScreen(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                        is AmphibiansUiState.Success -> AmphibianList(
                            listAmphibianData = (viewModel.amphibiansUiState as AmphibiansUiState.Success).listAmphibianData,
                            modifier = Modifier.padding(innerPadding)
                        )
                        is AmphibiansUiState.Error -> ErrorScreen(
                            retryAction = viewModel::getListAmphibianData,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        modifier = modifier
    )
}