package dev.rasul.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rasul.weatherapp.entity.PlacesEntity
import dev.rasul.weatherapp.entity.WeatherEntity
import dev.rasul.weatherapp.model.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(
    private val weatherModel: WeatherModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                weatherModel = weatherModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class WeatherViewModel(
    private val weatherModel: WeatherModel
) : ViewModel() {

    private val _weatherLiveData = MutableLiveData<WeatherEntity>()
    val weatherLiveData: LiveData<WeatherEntity> by this::_weatherLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> by this::_errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> by this::_loadingLiveData

    fun getWeather(place: PlacesEntity) {
        _loadingLiveData.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            weatherModel.getWeather(
                lat = place.lat,
                lon = place.lon
            ).collect { result ->
                _loadingLiveData.postValue(false)
                if (result.isSuccess) {
                    _weatherLiveData.postValue(result.getOrNull())
                } else {
                    _errorLiveData.postValue(result.exceptionOrNull())
                }
            }
        }
    }
}