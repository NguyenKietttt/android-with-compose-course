package com.example.busschedule.data

class BusScheduleRepository(private val busScheduleDAO: BusScheduleDao) {
    fun getAll(): List<BusSchedule> = busScheduleDAO.getAll()
    fun getItem(stopName: String): List<BusSchedule> = busScheduleDAO.getItem(stopName)
}