package com.example.weatherapp.screens.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.models.Location
import com.example.weatherapp.models.LocationDao
import com.example.weatherapp.network.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_book_mark.*

/**
 * Created by shande on 10-01-2021.
 */
class BookMarkFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var selectedLocation: LatLng
    private lateinit var marker: Marker
    private lateinit var viewModel: BookMarkViewModel
    private lateinit var bookMarkFragmentListener: BookMarkFragmentListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initViewModel()
        observeData()
        btnBookMark.setOnClickListener {
            val location: Location = Location().apply {
                lat = selectedLocation.latitude
                long = selectedLocation.longitude
            }
            viewModel.saveLocation(location = location)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            BookMarkViewModelFactory(BookMarkRepository((LocationDao)))
        ).get(BookMarkViewModel::class.java)
    }

    private fun observeData() {
        viewModel.locationData.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    bookMarkFragmentListener.onLocationBookMarked()
                }

                Status.FAILURE -> {

                }
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener { latLong ->
            setLocation(latLong)
        }
        // Add a marker in Sydney and move the camera
        setLocation(LatLng(-34.0, 151.0))
    }

    private fun setLocation(latLong: LatLng) {
        if (::marker.isInitialized) {
            marker.remove()
        }
        selectedLocation = latLong
        marker =
            mMap.addMarker(MarkerOptions().position(selectedLocation).title("Marker in Sydney"))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(selectedLocation))
    }

    fun setListener(bookMarkFragmentListener: BookMarkFragmentListener) {
        this.bookMarkFragmentListener = bookMarkFragmentListener
    }

    interface BookMarkFragmentListener {
        fun onLocationBookMarked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment BookMarkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            BookMarkFragment()

        const val TAG = "BookMarkFragmentTag"
    }

}