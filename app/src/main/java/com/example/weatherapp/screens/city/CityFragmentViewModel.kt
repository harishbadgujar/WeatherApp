package com.example.weatherapp.screens.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Location
import com.example.weatherapp.network.ApiResponse
import com.example.weatherapp.network.Resource
import com.example.weatherapp.network.Status

/**
 * Created by shande on 13-01-2021.
 */
class CityFragmentViewModel(private val cityFragmentRepository: CityFragmentRepository) :
    ViewModel() {

    val weatherData = MutableLiveData<Resource<ApiResponse>>()

    fun getCityWeatherDetails(location: Location) {
        weatherData.value = Resource(status = Status.LOADING)
        cityFragmentRepository.getCityWeatherDetails(location,
            onSuccess = { apiResponse ->
                weatherData.value = Resource(status = Status.SUCCESS, data = apiResponse)
            }, onFailed = {
                weatherData.value = Resource(status = Status.FAILURE)
            })
    }

}