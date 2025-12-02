package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmphibiansTheme {
        Greeting("Android")
    }
}