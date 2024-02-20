package com.src.presentation.views.onBoarding_interest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.domain.usecase.GetSelectedInterestsUseCase
import com.src.domain.usecase.SetSelectedInterestsUseCase
import com.src.domain.usecase.UpdateFirstLaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingInterestViewModel @Inject constructor(
    private val getSelectedInterestsUseCase: GetSelectedInterestsUseCase,
    private val setSelectedInterestsUseCase: SetSelectedInterestsUseCase,
    private val updateFirstLaunchUseCase: UpdateFirstLaunchUseCase
) : ViewModel() {

    private val _selectedInterests = MutableStateFlow<Set<String>>(emptySet())
    val selectedInterests: StateFlow<Set<String>> = _selectedInterests.asStateFlow()

    init {
        viewModelScope.launch {
            getSelectedInterestsUseCase().collect { interests ->
                _selectedInterests.value = interests
            }
        }
    }

    fun updateSelectedInterests(newInterest: String, isSelected: Boolean) {
        val currentInterests = _selectedInterests.value
        val updatedInterests = if (isSelected) {
            currentInterests + newInterest
        } else {
            currentInterests - newInterest
        }

        viewModelScope.launch {
            setSelectedInterestsUseCase(updatedInterests)
        }
    }

    fun updateFirstLaunchToFalse() {
        viewModelScope.launch {
            updateFirstLaunchUseCase(false)
        }
    }
}
