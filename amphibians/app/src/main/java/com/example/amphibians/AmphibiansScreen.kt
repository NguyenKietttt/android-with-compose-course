package com.example.amphibians

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.AmphibianData

@Composable
fun AmphibianCard(
    amphibianData: AmphibianData,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = amphibianData.name,
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibianData.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = amphibianData.description)
        }
    }
}