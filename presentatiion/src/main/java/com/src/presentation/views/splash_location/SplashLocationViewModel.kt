package com.src.presentation.views.splash_location


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.src.data.repository.GeocodingRepository
import com.src.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashLocationViewModel @Inject constructor(private val locationRepository: LocationRepository, private val geocodingRepository: GeocodingRepository) :
    ViewModel() {
    private val _userLocation = MutableLiveData<String>()
    val userLocation: LiveData<String> = _userLocation


    fun updateLocation(coordinates: Pair<Double, Double>) {
        viewModelScope.launch {
            val address = geocodingRepository.getAddressFromLocation(LatLng(coordinates.first, coordinates.second))
            Log.d("SplashLocationViewModel", "repository: $address")
            locationRepository.setLocation(address)
            _userLocation.value = address
        }
    }
            //repository.setLocation(address)
}
