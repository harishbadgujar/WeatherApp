package com.example.weatherapp.database

import android.database.sqlite.SQLiteDatabase
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.models.Location
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

/**
 * Created by shande on 11-01-2021.
 */

private const val DB_NAME = "WeatherDatabase"
private const val DB_VERSION = 1

object DatabaseHelper : OrmLiteSqliteOpenHelper(WeatherApplication.instance, DB_NAME, null, DB_VERSION) {
    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Location::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {

    }

}