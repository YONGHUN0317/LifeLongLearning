package com.src.presentation.views.splash_interest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.data.repository.InterestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashInterestViewModel @Inject constructor(private val repository: InterestRepository) : ViewModel() {

    private val _selectedInterests = MutableLiveData<Set<String>>()
    val selectedInterests: LiveData<Set<String>> = _selectedInterests

    fun updateSelectedInterests(newInterest: String, isSelected: Boolean) {
        val currentInterests = _selectedInterests.value ?: setOf()
        val updatedInterests = if (isSelected) {
            currentInterests + newInterest
        } else {
            currentInterests - newInterest
        }

        viewModelScope.launch {
            Log.d("SplashInterestViewModel", "repository: $updatedInterests")
            repository.setSelectedInterests(updatedInterests)
            _selectedInterests.value = updatedInterests
        }
    }
}
