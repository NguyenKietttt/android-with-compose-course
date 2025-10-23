package com.example.artspace

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(start = 24.dp, end = 24.dp)
    ) {
        var index by remember { mutableIntStateOf(1) }

        val pictureId: Int
        val artNameId: Int
        val artistId: Int
        val publishedYearId: Int
        when (index) {
            1 -> {
                pictureId = R.drawable.art_1
                artNameId = R.string.art_1_name
                artistId = R.string.art_1_artist
                publishedYearId = R.string.art_1_published_year
            }
            2 -> {
                pictureId = R.drawable.art_2
                artNameId = R.string.art_2_name
                artistId = R.string.art_2_artist
                publishedYearId = R.string.art_2_published_year
            }
            else -> {
                pictureId = R.drawable.art_3
                artNameId = R.string.art_3_name
                artistId = R.string.art_3_artist
                publishedYearId = R.string.art_3_published_year
            }
        }

        Picture(
            pictureId = pictureId,
            modifier = Modifier.weight(7.0f)
        )
        Spacer(Modifier.height(32.dp))
        Description(
            artNameId = artNameId,
            artistId = artistId,
            publishedYearId = publishedYearId,
            modifier = Modifier.weight(1.0f)
        )
        NavigationButtons(
            onPreviousButtonClicked = {
                if (index <= 1) index = 1 else index--
            },
            onNextButtonClicked = {
                if (index >= 3) index = 3 else index++
            },
            modifier = Modifier.weight(1.0f)
        )
    }
}

@Composable
fun Picture(
    pictureId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Image(
            painter = painterResource(pictureId),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
fun Description(
    artNameId: Int,
    artistId: Int,
    publishedYearId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.background(Color(0xffecebf3))
    ) {
        Text(
            text = stringResource(artNameId),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append(stringResource(artistId))
                }
                append(" ")
                append("(")
                append(stringResource(publishedYearId))
                append(")")
            },
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
    }
}

@Composable
fun NavigationButtons(
    onPreviousButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onPreviousButtonClicked,
            modifier = Modifier.size(120.dp, 40.dp)
        ) {
            Text("Previous")
        }
        Button(
            onClick = onNextButtonClicked,
            modifier = Modifier.size(120.dp, 40.dp)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8a", showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ArtSpaceApp(modifier = Modifier.padding(innerPadding))
        }
    }
}