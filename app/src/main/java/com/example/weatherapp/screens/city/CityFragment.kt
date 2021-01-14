package com.example.weatherapp.screens.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.models.Location
import com.example.weatherapp.network.Status
import java.text.SimpleDateFormat
import java.util.*

private const val LOCATION = "location"

/**
 * Created by shande on 10-01-2021.
 */

const val MM_DD_YY = "MM/dd/yy"

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
        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    isShowProgress(true)
                }

                Status.SUCCESS -> {
                    isShowProgress(false)
                    binding.group.visibility = VISIBLE
                    it.data?.let { apiResponse ->
                        with(binding) {

                            apiResponse.weather?.let {
                                tvCloud.text = it[0].main.plus(" ").plus(it[0].description)
                            }

                            apiResponse.main?.let {main->
                                tvTemperature.append(main.temp.toString())
                            }

                            apiResponse.wind?.let {wind->
                                tvWind.append(" ${wind.speed}")
                            }

                            apiResponse.sys?.let { sys ->
                                tvSunRise.append(" ${sys.sunrise.toWellFormattedDate(
                                    apiResponse.timezone.toString(),
                                    MM_DD_YY
                                )}")

                                tvSunSet.append(" ${sys.sunset.toWellFormattedDate(
                                    apiResponse.timezone.toString(),
                                    MM_DD_YY
                                )}")

                            }

                        }
                    }
                }

                Status.FAILURE -> {

                }
            }
        })
    }

    private fun isShowProgress(isShow: Boolean) {
        with(binding.pbCity) {
            if (isShow) {
                visibility = VISIBLE
                animate()
            } else {
                visibility = GONE
            }
        }
    }

    private fun Long.toWellFormattedDate(timeZone: String, dateFormat: String): String {
        if (this > 0 && timeZone.isNotEmpty()) {
            val calendar = GregorianCalendar(TimeZone.getTimeZone(timeZone))
            calendar.time = Date(this * 1000)
            val formatter = SimpleDateFormat(dateFormat, Locale.US)
            formatter.timeZone = TimeZone.getTimeZone(timeZone)
            val unwantedTimeRemoved = formatter.format(calendar.time)
            return unwantedTimeRemoved.replace(" 00:00", "")
        }
        return ""
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

        const val TAG = "CityFragmentTag"
    }
}