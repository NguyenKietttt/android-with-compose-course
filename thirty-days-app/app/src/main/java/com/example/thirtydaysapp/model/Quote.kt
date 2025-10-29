package com.example.thirtydaysapp.model

import androidx.annotation.StringRes

data class Quote(
    val day: Int,
    @StringRes val title: Int,
    @StringRes val message: Int,
)
