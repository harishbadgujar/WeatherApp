package com.example.weatherapp.models

import android.os.Parcel
import android.os.Parcelable
import com.example.weatherapp.database.DatabaseHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by shande on 11-01-2021.
 */
@DatabaseTable
class Location() : Parcelable {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField(columnName = NAME)
    var name: String = ""

    @DatabaseField(columnName = LATITUDE)
    var lat: Double = 0.0

    @DatabaseField(columnName = LONGITUDE)
    var long: Double = 0.0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString() ?: ""
        lat = parcel.readDouble()
        long = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(lat)
        parcel.writeDouble(long)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }

        const val NAME = "name"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }
}

object LocationDao {
    private val dao: Dao<Location, Int> = DatabaseHelper.getDao(Location::class.java)

    fun saveLocation(location: Location) {
        dao.create(location)
    }

    fun getAllLocations(): List<Location> {
        return dao.queryForAll()
    }

}