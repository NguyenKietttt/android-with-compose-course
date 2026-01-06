package com.example.busschedule.data

import android.content.Context

class AppContainer(private val context: Context) {
    val busScheduleRepository by lazy {
        BusScheduleRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
    }
}