package dev.rasul.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dev.rasul.weatherapp.Constants
import dev.rasul.weatherapp.PreferenceUtil
import dev.rasul.weatherapp.R
import dev.rasul.weatherapp.adapter.DailyWeatherAdapter
import dev.rasul.weatherapp.databinding.FragmentMainBinding
import dev.rasul.weatherapp.viewModel.WeatherViewModel
import dev.rasul.weatherapp.viewModel.WeatherViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var mViewModel: WeatherViewModel


    private val mDailyWeatherAdapter: DailyWeatherAdapter = DailyWeatherAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
        initClickListeners()
    }

    private fun initViews() {
        binding.dailyWeatherRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mDailyWeatherAdapter
        }

        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            val currentPlace = PreferenceUtil.getSelectedPlace(requireContext())
            currentPlace?.let {
                mViewModel.getWeather(currentPlace)
            }
        }
    }

    private val lastSyncSdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH)

    private val sdf = SimpleDateFormat("dd MMMM, EEEE", Locale.ENGLISH)
    private val timeSdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    private fun initViewModel() {
        val currentPlace = PreferenceUtil.getSelectedPlace(requireContext())

        mViewModel = ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]

        mViewModel.weatherLiveData.observe(viewLifecycleOwner, { weatherEntity ->

            val currentWeather = weatherEntity?.current
            binding.txtTitle.text =
                currentPlace?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " " + sdf.format(
                    Date(currentWeather?.dt!!.toLong() * 1000)
                )

            Picasso.get()
                .load(Constants.baseUrlForIcons + "${currentWeather.weather?.icon}@4x.png")
                .into(binding.imgWeather)

            binding.txtTemperature.text = "${currentWeather.temp?.roundToInt()}°C"

            binding.txtCurrentWeather.text =
                currentWeather.weather?.description?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }

            weatherEntity.lastSync?.let {
                binding.txtLastSync.text = lastSyncSdf.format(it)
            }
            mDailyWeatherAdapter.dailyItems = weatherEntity.daily ?: emptyList()


            binding.txtHumidity.text = "Humidity: ${currentWeather.humidity}%"
            binding.txtWindSpeed.text = "Wind Speed: ${currentWeather.windSpeed} m/s"
            binding.txtFeelsLike.text = "Feels Like: ${currentWeather.temp?.roundToInt()}°C"
            binding.txtSunrise.text =
                "Sunrise: ${timeSdf.format(Date(currentWeather.sunrise!!.toLong() * 1000))}"
            binding.txtSunset.text =
                "Sunset: ${timeSdf.format(Date(currentWeather.sunset!!.toLong() * 1000))}"

        })
        mViewModel.errorLiveData.observe(viewLifecycleOwner, { throwable ->
            Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
        })


        mViewModel.loadingLiveData.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })

        currentPlace?.let {
            mViewModel.getWeather(currentPlace)
        }

    }

    private fun initClickListeners() {
        binding.btnSearch.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.container, SearchPlaceFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}