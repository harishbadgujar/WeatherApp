package com.example.weatherapp.screens.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.screens.home.HomeFragmentViewModel

/**
 * Created by shande on 13-01-2021.
 */
class CityViewModelFactory(private val cityFragmentRepository: CityFragmentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityFragmentViewModel(cityFragmentRepository) as T
    }
}