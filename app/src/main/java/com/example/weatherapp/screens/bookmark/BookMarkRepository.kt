package com.example.weatherapp.screens.bookmark


import com.example.weatherapp.models.Location
import com.example.weatherapp.models.LocationDao

/**
 * Created by shande on 10-01-2021.
 */
class BookMarkRepository(private val locationDao: LocationDao) {

    fun saveLocation(location: Location) {
        locationDao.saveLocation(location)
    }
}