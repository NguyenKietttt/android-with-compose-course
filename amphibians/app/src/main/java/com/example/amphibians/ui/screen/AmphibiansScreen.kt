package com.example.amphibians.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianData

@Composable
fun AmphibianList(
    listAmphibianData: List<AmphibianData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
    ) {
        items(listAmphibianData) { amphibianData ->
            AmphibianCard(amphibianData = amphibianData)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun AmphibianCard(
    amphibianData: AmphibianData,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = amphibianData.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibianData.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_connection_error),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = amphibianData.description,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}