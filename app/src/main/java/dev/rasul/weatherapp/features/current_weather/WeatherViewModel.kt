package dev.rasul.weatherapp.features.current_weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rasul.weatherapp.data.WeatherRepository
import dev.rasul.weatherapp.data.db.entity.PlacesEntity
import dev.rasul.weatherapp.data.db.entity.WeatherEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                weatherRepository = weatherRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class WeatherViewModel(
    private val weatherRepository: WeatherRepository
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
            weatherRepository.getWeather(
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