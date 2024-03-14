package com.src.presentation.views.onBoarding_location


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.domain.usecase.GetAddressFromLocationUseCase
import com.src.domain.usecase.GetLocationUseCase
import com.src.domain.usecase.SetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingLocationViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val setLocationUseCase: SetLocationUseCase,
    private val getAddressFromLocationUseCase: GetAddressFromLocationUseCase
) : ViewModel() {

    private val _userLocation = MutableStateFlow("")
    val userLocation: StateFlow<String> = _userLocation

    fun updateLocation(coordinates: Pair<Double, Double>) {
        viewModelScope.launch {
            val address = getAddressFromLocationUseCase(coordinates.first, coordinates.second).first()
            Log.d("SplashLocationViewModel", "repository: $address")
            setLocationUseCase(address, coordinates.first, coordinates.second)
            /*getLocationUseCase().collect{ locationValue ->
                _userLocation.value = locationValue.first
            }*/
        }
    }
}
