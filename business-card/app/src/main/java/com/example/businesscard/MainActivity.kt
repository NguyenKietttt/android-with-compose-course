package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Card(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Card(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFd2e7d4))
    ) {
        Body(modifier = Modifier.weight(3.0f))
        Footer(modifier = Modifier.weight(1.0f))
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Box(Modifier
            .size(128.dp)
            .fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.android_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(R.string.owner_name),
            fontSize = 46.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(R.string.owner_title),
            color = Color(0xFF045b30)
        )
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        IconTextRow(icon = Icons.Default.Call, text = stringResource(R.string.owner_phone))
        IconTextRow(icon = Icons.Default.Share, text = stringResource(R.string.owner_share))
        IconTextRow(icon = Icons.Default.Email, text = stringResource(R.string.owner_mail))
    }
}

@Composable
fun IconTextRow(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF016d3b),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_8a", showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Card(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}