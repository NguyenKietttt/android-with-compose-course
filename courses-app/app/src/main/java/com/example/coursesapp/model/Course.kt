package com.example.coursesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Course(
    @StringRes val titleResourceId: Int,
    val enrollment: Int,
    @DrawableRes val imageResourceId: Int
)
