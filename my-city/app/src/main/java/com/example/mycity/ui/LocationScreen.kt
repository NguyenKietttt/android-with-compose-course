package com.example.mycity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.DataSource
import com.example.mycity.model.Location

@Composable
fun LocationScreen(
    location: Location,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(
            start = 16.dp,
            end = 16.dp
        )
    ) {
        Text(text = location.description)
    }
}

@Preview
@Composable
fun LocationScreenPreview() {
    LocationScreen(
        location = DataSource.getDefaultLocation()
    )
}