package com.example.mycity.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.data.DataSource
import com.example.mycity.model.Location

@Composable
fun LocationScreen(
    location: Location,
    modifier: Modifier = Modifier
) {
    Text(
        text = location.description,
        modifier = modifier
    )
}

@Preview
@Composable
fun LocationScreenPreview() {
    LocationScreen(
        location = DataSource.getDefaultLocation()
    )
}