package com.example.weatherapp.screens.city

import com.example.weatherapp.models.Location
import com.example.weatherapp.network.ApiClient
import com.example.weatherapp.network.ApiResponse
import com.example.weatherapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by shande on 13-01-2021.
 */
class CityFragmentRepository {

    fun getCityWeatherDetails(
        location: Location,
        onSuccess: (apiResponse: ApiResponse?) -> Unit,
        onFailed: () -> Unit
    ) {
        val request = ApiClient.createService(ApiService::class.java)
        val apiCall = request.getCityWeatherDetails(
            lat = location.lat,
            lon = location.long,
            appId = ApiClient.APP_ID
        )
        apiCall.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onFailed()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                onSuccess(response.body())
            }

        })
    }

}