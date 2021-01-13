package com.example.weatherapp.screens.home

import com.example.weatherapp.models.Location
import com.example.weatherapp.models.LocationDao

/**
 * Created by shande on 11-01-2021.
 */
class HomeFragmentRepository(private val locationDao: LocationDao) {

    fun getAllBookMarkedLocations():List<Location>{
        return locationDao.getAllLocations()
    }

}