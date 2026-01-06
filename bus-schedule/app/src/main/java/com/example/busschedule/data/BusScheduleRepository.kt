package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class BusScheduleRepository(private val busScheduleDAO: BusScheduleDao) {
    fun getAll(): Flow<List<BusSchedule>> = busScheduleDAO.getAll()
    fun getItem(stopName: String): Flow<List<BusSchedule>> = busScheduleDAO.getItem(stopName)
}