package com.src.presentation.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.src.domain.model.LectureEntity
import com.src.domain.usecase.GetLecturesUseCase
import com.src.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Search view model
 *
 * @property getLecturesUseCase
 * @property getSearchUseCase
 * @constructor Create empty Search view model
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getLecturesUseCase: GetLecturesUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val currentQuery = MutableStateFlow<String?>(null)

    /**
     *  데이터가 있는 경우 없는 경우 구분
     */

    val lectures: Flow<PagingData<LectureEntity>> = currentQuery.flatMapLatest { query ->
        if (query.isNullOrEmpty()) {
            getLecturesUseCase().cachedIn(viewModelScope)
        } else {
            getSearchUseCase(query).cachedIn(viewModelScope)
        }
    }

    fun searchLectures(query: String?) {
        currentQuery.value = query
    }
}