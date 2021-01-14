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
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addHomeFragment()
    }

    private fun addHomeFragment() {
        val homeFragment = HomeFragment.newInstance()
        homeFragment.setListener(homeFragmentListener)
        addFragment(homeFragment, HomeFragment.TAG)
    }

    private fun addHelpFragment() {
        addFragment(HelpFragment.newInstance(), HelpFragment.TAG)
    }

    private fun addBookMarkFragment() {
        val bookMarkFragment = BookMarkFragment.newInstance()
        bookMarkFragment.setListener(bookMarkFragmentListener)
        addFragment(bookMarkFragment, BookMarkFragment.TAG)
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

    private fun popUpFragment(tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(tag)?.let {
            fragmentTransaction.remove(it)
            fragmentTransaction.commit()
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

    private val homeFragmentListener = object : HomeFragment.HomeFragmentListener {
        override fun onFabClick() {
            addBookMarkFragment()
        }

        override fun onLocationSelected(location: Location) {
            addCityFragment(location)
        }

    }

    private val bookMarkFragmentListener = object : BookMarkFragment.BookMarkFragmentListener {
        override fun onLocationBookMarked() {
            popUpFragment(BookMarkFragment.TAG)
        }
    }
}
