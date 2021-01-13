package com.example.weatherapp.screens.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.models.Location

private const val LOCATION = "location"

/**
 * Created by shande on 10-01-2021.
 */
class CityFragment : Fragment() {
    private var location: Location? = null
    private lateinit var binding: FragmentCityBinding
    private lateinit var viewModel: CityFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            location = it.getParcelable(LOCATION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = location?.lat.toString()
        initUI()
        initViewModel()
        observeData()
        location?.let {
            viewModel.getCityWeatherDetails(it)
        }
    }

    private fun initUI() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, CityViewModelFactory(CityFragmentRepository())).get(
            CityFragmentViewModel::class.java
        )
    }

    private fun observeData() {

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param location Parameter 1.
         * @return A new instance of fragment CityFragment.
         */
        @JvmStatic
        fun newInstance(location: Location) =
            CityFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LOCATION, location)
                }
            }

        const val TAG = "TAG"
    }
}