package com.src.presentation.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.domain.repository.InterestRepository
import com.src.domain.usecase.GetSelectedInterestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSelectedInterestsUseCase: GetSelectedInterestsUseCase
) : ViewModel() {
    // Initial states for the flows
    private val _selectedInterests = MutableStateFlow<List<String>>(emptyList())
    val selectedInterests: StateFlow<List<String>> = _selectedInterests.asStateFlow()

    private val _remainingInterests = MutableStateFlow<List<String>>(emptyList())
    val remainingInterests: StateFlow<List<String>> = _remainingInterests.asStateFlow()

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
        }
    }
}
