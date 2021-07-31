package dev.rasul.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rasul.weatherapp.entity.PlacesEntity
import dev.rasul.weatherapp.model.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchPlaceViewModelFactory @Inject constructor(
    private val weatherModel: WeatherModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPlaceViewModel::class.java)) {
            return SearchPlaceViewModel(
                weatherModel = weatherModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class SearchPlaceViewModel(
    private val weatherModel: WeatherModel
) : ViewModel() {

    private val _placesLiveData = MutableLiveData<List<PlacesEntity>>()
    val placesLiveData: LiveData<List<PlacesEntity>> by this::_placesLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> by this::_errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> by this::_loadingLiveData


    fun getPlaces(query: String) {
        _loadingLiveData.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            weatherModel.getPlaces(
                query = query
            ).collect { result ->
                _loadingLiveData.postValue(false)
                if (result.isSuccess) {
                    _placesLiveData.postValue(result.getOrNull())
                } else {
                    _errorLiveData.postValue(result.exceptionOrNull())
                }
            }
        }
    }

}