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

@Composable
fun ListCategoryScreen(
    listCategory: List<String>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(listCategory) { category ->
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = { onClick(category) })
            ) {
                Text(text = category)
            }
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
fun ListCategoryScreenPreview() {
    ListCategoryScreen(
        listCategory = DataSource.getListCategory(),
        onClick = { }
    )
}