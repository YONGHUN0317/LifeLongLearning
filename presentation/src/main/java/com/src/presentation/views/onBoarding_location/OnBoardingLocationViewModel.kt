package com.src.presentation.views.onBoarding_location


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.src.data.repository.GeocodingRepository
import com.src.data.repository.LocationRepository
import com.src.domain.usecase.GetAddressFromLocationUseCase
import com.src.domain.usecase.GetLocationUseCase
import com.src.domain.usecase.SetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingLocationViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val setLocationUseCase: SetLocationUseCase,
    private val getAddressFromLocationUseCase: GetAddressFromLocationUseCase
) : ViewModel() {

    private val _userLocation = MutableLiveData<String>()
    val userLocation: LiveData<String> = _userLocation

    fun updateLocation(coordinates: Pair<Double, Double>) {
        viewModelScope.launch {
            val address = getAddressFromLocationUseCase(coordinates.first, coordinates.second).first()
            Log.d("SplashLocationViewModel", "repository: $address")
            setLocationUseCase(address)
            _userLocation.value = address
        }
    }
}
