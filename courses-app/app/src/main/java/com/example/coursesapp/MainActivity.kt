package com.example.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.data.Datasource
import com.example.coursesapp.model.Course
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseGrid(
                        courseList = Datasource.courses,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CourseGrid(courseList: List<Course>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(courseList) { course ->
            CourseCard(
                course = course,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(course.imageResourceId),
                contentDescription = stringResource(course.titleResourceId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(68.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = stringResource(course.titleResourceId),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = course.enrollment.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CourseCardPreview() {
    CourseCard(Course(R.string.photography, 321, R.drawable.photography))
}