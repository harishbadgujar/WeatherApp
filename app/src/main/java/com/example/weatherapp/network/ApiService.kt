package com.example.weatherapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by shande on 13-01-2021.
 */
interface ApiService {

    @GET("weather")
    fun getCityWeatherDetails(
        @Query(LAT) lat: Double,
        @Query(LON) lon: Double,
        @Query(APP_ID) appId: String
    ): Call<ApiResponse>

    companion object {
        const val APP_ID = "appid"
        const val LAT = "lat"
        const val LON = "lon"
    }

}