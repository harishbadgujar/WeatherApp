package com.example.weatherapp

import android.app.Application

/**
 * Created by shande on 10-01-2021.
 */
class WeatherApplication : Application() {

    companion object {
        lateinit var instance: WeatherApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}