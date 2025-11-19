package com.example.mycity.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.DataSource
import com.example.mycity.model.Location

@Composable
fun ListLocationScreen(
    listLocation: List<Location>,
    onClick: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(listLocation) { location ->
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = { onClick(location) })
            ) {
                Text(text = location.name)
            }
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
fun ListLocationScreenPreview() {
    val defaultCategory = DataSource.getDefaultCategory()
    ListLocationScreen(
        listLocation = DataSource.getListLocationByCategory(defaultCategory),
        onClick = { }
    )
}