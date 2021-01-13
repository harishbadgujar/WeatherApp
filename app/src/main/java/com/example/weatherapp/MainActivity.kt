package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.models.Location
import com.example.weatherapp.screens.bookmark.BookMarkFragment
import com.example.weatherapp.screens.city.CityFragment
import com.example.weatherapp.screens.help.HelpFragment
import com.example.weatherapp.screens.home.HomeFragment

/**
 * Created by shande on 02-01-2021.
 */
class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addHomeFragment()
    }

    private fun addHomeFragment() {
        val homeFragment = HomeFragment.newInstance()
        homeFragment.setListener(this)
        addFragment(homeFragment, HomeFragment.TAG)
    }

    private fun addHelpFragment() {
        addFragment(HelpFragment.newInstance(), HelpFragment.TAG)
    }

    private fun addBookMarkFragment() {
        addFragment(BookMarkFragment.newInstance(), BookMarkFragment.TAG)
    }

    private fun addCityFragment(location: Location) {
        addFragment(CityFragment.newInstance(location = location), CityFragment.TAG)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            add(binding.fragmentContainer.id, fragment, tag)
            setReorderingAllowed(true)
            if (fragment !is HomeFragment) {
                addToBackStack(null)
            }
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.help -> {
                addHelpFragment()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFabClick() {
        addBookMarkFragment()
    }

    override fun onLocationSelected(location: Location) {
        addCityFragment(location)
    }

}