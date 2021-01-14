package com.example.weatherapp.network

/**
 * Created by shande on 13-01-2021.
 */
class ApiResponse {

    val coord: Coord? = null
    val weather: List<Weather>? = null
    val base: String = ""
    val main: Main? = null
    val visibility: Long = 0
    val wind: Wind? = null
    val clouds: Cloud? = null
    val dt: Long = 0
    val sys: Sys? = null
    val timezone: Long = 0
    val id: Int = 0
    val name: String = ""
    val cod: Int = 0
}

class Coord {
    val lat: Double = 0.0
    val lon: Double = 0.0
}

class Weather {
    val id: Int = 0
    val main: String = ""
    val description: String = ""
    val icon: String = ""
}

class Main {
    val temp: Float = 0F
    val feels_like: Float = 0F
    val temp_min: Float = 0F
    val temp_max: Float = 0F
    val pressure: Float = 0F
    val humidity: Float = 0F
}

class Wind {
    val speed: Float = 0F
    val deg: Float = 0F
    val gust: Float = 0F
}

class Cloud {
    val all: Float = 0F
}

class Sys {
    val type: Int = 0
    val id: Int = 0
    val country: String = ""
    val sunrise: Long = 0
    val sunset: Long = 0
}