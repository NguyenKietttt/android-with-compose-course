package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BusScheduleDao {
    @Query(value = "SELECT * FROM Schedule ORDER BY arrival_time")
    fun getAll(): List<BusSchedule>

    @Query(value = "SELECT * FROM Schedule WHERE stop_name == :stopName")
    fun getItem(stopName: String): List<BusSchedule>
}