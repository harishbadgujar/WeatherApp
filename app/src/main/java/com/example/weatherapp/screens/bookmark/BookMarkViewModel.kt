package com.example.weatherapp.screens.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Location
import com.example.weatherapp.network.Resource
import com.example.weatherapp.network.Status

/**
 * Created by shande on 10-01-2021.
 */
class BookMarkViewModel(private val bookMarkRepository: BookMarkRepository) : ViewModel() {

    val locationData = MutableLiveData<Resource<Boolean>>()

    fun saveLocation(location: Location) {
        locationData.value = Resource(status = Status.LOADING)
        bookMarkRepository.saveLocation(location)
        locationData.value = Resource(status = Status.SUCCESS, data = true)
    }

}