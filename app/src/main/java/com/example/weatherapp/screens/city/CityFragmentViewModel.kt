package com.example.weatherapp.screens.city

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Location

/**
 * Created by shande on 13-01-2021.
 */
class CityFragmentViewModel(private val cityFragmentRepository: CityFragmentRepository) :
    ViewModel() {

    fun getCityWeatherDetails(location: Location) {
        cityFragmentRepository.getCityWeatherDetails(location)
    }

}