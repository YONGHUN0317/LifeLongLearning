package com.src.presentation.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.domain.usecase.GetLocationUseCase
import com.src.domain.usecase.GetSelectedInterestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main view model
 *
 * @property getSelectedInterestsUseCase
 * @property getLocationUseCase
 * @constructor Create empty Main view model
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSelectedInterestsUseCase: GetSelectedInterestsUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _selectedInterests = MutableStateFlow<List<String>>(emptyList())
    val selectedInterests: StateFlow<List<String>> = _selectedInterests.asStateFlow()

    private val _remainingInterests = MutableStateFlow<List<String>>(emptyList())
    val remainingInterests: StateFlow<List<String>> = _remainingInterests.asStateFlow()

    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location.asStateFlow()

    /**
     * 선택한 흥미 데이터 세팅
     */
    init {
        viewModelScope.launch {
            getSelectedInterestsUseCase().collect { interestsSet ->
                val interests = interestsSet.toList()
                _selectedInterests.value = interests

                val allInterests = listOf("프로그래밍", "여행", "음악", "영화", "스포츠",
                    "요리", "책", "영어", "역사", "투자", "심리", "운동",
                    "사진", "원예", "패션")
                _remainingInterests.value = allInterests - interests.toSet()
            }
            getLocationUseCase().collect{location ->
                _location.value = location.first
            }
        }
    }
}
