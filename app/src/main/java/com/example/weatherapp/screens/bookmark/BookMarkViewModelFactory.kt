package com.example.weatherapp.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by shande on 10-01-2021.
 */
class BookMarkViewModelFactory(private val bookMarkRepository: BookMarkRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookMarkViewModel(bookMarkRepository) as T
    }

}