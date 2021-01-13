package com.example.weatherapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by shande on 11-01-2021.
 */
class HomeFragmentViewModelFactory(private val homeFragmentRepository: HomeFragmentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(homeFragmentRepository) as T
    }
}