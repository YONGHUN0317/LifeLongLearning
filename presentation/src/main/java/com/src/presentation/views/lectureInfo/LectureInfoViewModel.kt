package com.src.presentation.views.lectureInfo

import android.location.Location
import com.src.domain.usecase.GetCoordinatesFromAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * 상세 정보 ViewModel
 */
@HiltViewModel
class LectureInfoViewModel @Inject constructor(
    private val getCoordinatesFromAddressUseCase: GetCoordinatesFromAddressUseCase
) : ViewModel() {
    private val _locationState = MutableStateFlow<Location?>(null)
    val locationState: StateFlow<Location?> = _locationState

    /**
     * 주소를 위도 경도로 변환
     */
    fun updateLocationForAddress(address: String) {
        viewModelScope.launch {
            try {
                val location = getCoordinatesFromAddressUseCase(address)
                _locationState.value = location
            } catch (e: Exception) {
                _locationState.value = null
            }
        }
    }
}