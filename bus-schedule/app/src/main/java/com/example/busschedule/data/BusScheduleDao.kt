package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query(value = "SELECT * FROM Schedule ORDER BY arrival_time")
    fun getAll(): Flow<List<BusSchedule>>

    @Query(value = "SELECT * FROM Schedule WHERE stop_name == :stopName")
    fun getItem(stopName: String): Flow<List<BusSchedule>>
}