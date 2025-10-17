package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(
                    topBar = { TopAppBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xfff9e44b),
            titleContentColor = Color.Black,
        ),
        title = {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        var step by remember { mutableIntStateOf(1) }
        var squeezeStep by remember { mutableIntStateOf(0)}

        val imageResource: Int
        val imageContentDescription: Int
        val textDescription: Int

        when (step) {
            1 -> {
                imageResource = R.drawable.lemon_tree
                imageContentDescription = R.string.lemon_tree_image_content_description
                textDescription = R.string.tap_lemon_tree
            }
            2 -> {
                imageResource = R.drawable.lemon_squeeze
                imageContentDescription = R.string.lemon_image_content_description
                textDescription = R.string.squeeze_lemon

                if (squeezeStep <= 0) squeezeStep = (2..4).random()
            }
            3 -> {
                imageResource = R.drawable.lemon_drink
                imageContentDescription = R.string.glass_of_lemon_image_content_description
                textDescription = R.string.drink_lemonade
            }
            else -> {
                imageResource = R.drawable.lemon_restart
                imageContentDescription = R.string.empty_glass_image_content_description
                textDescription = R.string.empty_glass
            }
        }

        Button(
            onClick = {
                if (step == 2) {
                    squeezeStep--
                }

                if (squeezeStep <= 0) {
                    step++
                    if (step > 4) step = 1
                }
            },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffc4ecd2))
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageContentDescription)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(textDescription),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_8a", showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Scaffold(
            topBar = { TopAppBar() },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            LemonadeApp(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}