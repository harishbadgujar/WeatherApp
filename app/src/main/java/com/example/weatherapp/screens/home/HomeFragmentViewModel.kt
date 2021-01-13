package com.example.weatherapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Location

/**
 * Created by shande on 11-01-2021.
 */
class HomeFragmentViewModel(private val homeFragmentRepository: HomeFragmentRepository) :
    ViewModel() {

    val locationData = MutableLiveData<List<Location>>()

    fun getAllBookMarkedLocations() {
        locationData.value = homeFragmentRepository.getAllBookMarkedLocations()
    }

}