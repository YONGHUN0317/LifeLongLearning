package com.src.presentation.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.src.domain.model.LectureEntity
import com.src.domain.usecase.GetLecturesUseCase
import com.src.domain.usecase.GetSearchUseCase
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
    private val getLecturesUseCase: GetLecturesUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    val lectures: SharedFlow<PagingData<LectureEntity>> = getLecturesUseCase.invoke()
        .cachedIn(viewModelScope)
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    private val _searchResults = MutableStateFlow<List<LectureEntity>>(emptyList())
    val searchResults: StateFlow<List<LectureEntity>> = _searchResults.asStateFlow()

    fun searchLectures(query: String) {
        viewModelScope.launch {
            getSearchUseCase.invoke(query).collect { results ->
                _searchResults.value = results
            }
        }
    }
}