package com.example.weatherapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by shande on 13-01-2021.
 */
object ApiClient {
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private lateinit var apiClient: Retrofit
    private val gson = GsonBuilder().create()
    const val APP_ID = "fae7190d7e6433ec3a45285ffcf55c86"

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun <T> createService(serviceClass: Class<T>): T {
        if (!::apiClient.isInitialized) {
            apiClient = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return apiClient.create(serviceClass)
    }
}