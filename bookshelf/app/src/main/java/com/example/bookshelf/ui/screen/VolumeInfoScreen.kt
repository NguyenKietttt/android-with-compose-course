package com.example.bookshelf.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.model.VolumeInfo

@Composable
fun VolumeInfoScreen(
    volumeInfo: VolumeInfo?,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier. padding(8.dp)) {
        item {
            Text(
                text = volumeInfo?.title.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Text(
                text = volumeInfo?.authors.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Text(
                text = volumeInfo?.publishedDate.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item { Spacer(Modifier.height(10.dp)) }
        item { Text(text = volumeInfo?.description.toString()) }
    }
}