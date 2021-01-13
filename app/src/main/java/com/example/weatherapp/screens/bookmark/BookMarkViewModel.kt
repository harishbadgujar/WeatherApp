package com.example.weatherapp.screens.bookmark

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Location

/**
 * Created by shande on 10-01-2021.
 */
class BookMarkViewModel(private val bookMarkRepository: BookMarkRepository) : ViewModel() {

    fun saveLocation(location: Location) {
        bookMarkRepository.saveLocation(location)
    }

}