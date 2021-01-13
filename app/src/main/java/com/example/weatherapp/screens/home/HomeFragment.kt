package com.example.weatherapp.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapters.LocationAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.models.Location
import com.example.weatherapp.models.LocationDao


/**
 * Created by shande on 10-01-2021.
 */
class HomeFragment : Fragment() {
    private lateinit var homeFragmentListener: HomeFragmentListener
    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeFragmentListener = homeFragmentListener
        initUI()
        initViewModel()
        observeData()
        viewModel.getAllBookMarkedLocations()
    }

    private fun initUI() {
        activity?.let {
            locationAdapter = LocationAdapter(it, ArrayList(), onLocationSelected = { location ->
                homeFragmentListener.onLocationSelected(location = location)
            })
            with(binding.rcvLocations) {
                this.adapter = locationAdapter
                val dividerItemDecoration = DividerItemDecoration(it, LinearLayoutManager.VERTICAL)
                addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            HomeFragmentViewModelFactory(HomeFragmentRepository((LocationDao)))
        ).get(HomeFragmentViewModel::class.java)
    }

    private fun observeData() {
        viewModel.locationData.observe(viewLifecycleOwner, Observer { locations ->
            locationAdapter.addLocations(locations)
        })
    }

    fun setListener(homeFragmentListener: HomeFragmentListener) {
        this.homeFragmentListener = homeFragmentListener
    }

    interface HomeFragmentListener {
        fun onFabClick()
        fun onLocationSelected(location: Location)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() =
            HomeFragment()

        const val TAG = "HomeFragmentTag"
    }
}
