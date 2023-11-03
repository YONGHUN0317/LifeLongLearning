package com.src.presentation.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.src.data.repository.InterestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: InterestRepository) : ViewModel() {
    private val _selectedInterests = MutableLiveData<List<String>>()
    val selectedInterests: LiveData<List<String>> = _selectedInterests

    private val _remainingInterests = MutableLiveData<List<String>>()
    val remainingInterests: LiveData<List<String>> = _remainingInterests

    init {
        viewModelScope.launch {
            val interests = repository.getSelectedInterests().toList()
            _selectedInterests.value = interests

            // Assuming 'allInterests' is a constant list of all possible interests
            val allInterests = listOf("프로그래밍", "여행", "음악", "영화", "스포츠",
                "요리", "책", "영어", "역사", "투자", "심리", "운동",
                "사진", "원예", "패션")
            _remainingInterests.value = allInterests - interests.toSet()
        }
    }
}
