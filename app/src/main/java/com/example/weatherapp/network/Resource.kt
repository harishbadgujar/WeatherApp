package com.example.weatherapp.network

/**
 * Created by shande on 13-01-2021.
 */
data class Resource<out T>(val status: Status, val data: T?=null, val message: String?=null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.FAILURE, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}
