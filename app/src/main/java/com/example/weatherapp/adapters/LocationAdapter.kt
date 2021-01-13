package com.example.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.Location
import kotlinx.android.synthetic.main.item_location.view.*

/**
 * Created by shande on 11-01-2021.
 */
class LocationAdapter(
    private var activity: FragmentActivity,
    private val locationList: ArrayList<Location>,
    val onLocationSelected: (location: Location) -> Unit
) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.item_location, parent, false)
        )
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(locationList[position]) {
            holder.tvLocationName.text = lat.toString()
            holder.parentLayout.setOnClickListener {
                onLocationSelected(this)
            }
        }
    }

    fun addLocations(locationList: List<Location>) {
        this.locationList.clear()
        this.locationList.addAll(locationList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val tvLocationName: TextView = itemView.tvLocationName
        val parentLayout: ConstraintLayout = itemView.parentLayout
    }
}