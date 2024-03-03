package com.src.presentation.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.src.domain.model.LectureEntity
import com.src.domain.usecase.GetLecturesUseCase
import com.src.domain.usecase.GetFilteredDataUseCase
import com.src.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getLecturesUseCase: GetLecturesUseCase,
    private val getFilteredDataUseCase: GetFilteredDataUseCase
) : ViewModel() {
    val lectures: SharedFlow<PagingData<LectureEntity>> = getLecturesUseCase.invoke()
        .cachedIn(viewModelScope)
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    private val _searchResults = MutableStateFlow<Resource<List<LectureEntity>>>(Resource.Loading())
    val searchResults: StateFlow<Resource<List<LectureEntity>>> = _searchResults.asStateFlow()

    fun searchLectures(query: String) {
        viewModelScope.launch {
            _searchResults.value = Resource.Loading()
            getFilteredDataUseCase.invoke(query).collect { result ->
                _searchResults.value = result
            }
        }
    }
}