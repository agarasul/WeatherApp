package dev.rasul.weatherapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.rasul.weatherapp.PreferenceUtil
import dev.rasul.weatherapp.R
import dev.rasul.weatherapp.databinding.ActivityMainBinding
import dev.rasul.weatherapp.ui.MainFragment
import dev.rasul.weatherapp.ui.SearchPlaceFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().apply {
            val currentPlace = PreferenceUtil.getSelectedPlace(applicationContext)
            if (currentPlace == null) {
                replace(R.id.container, SearchPlaceFragment())
            } else {
                replace(R.id.container, MainFragment())
            }
        }.commit()
    }

}