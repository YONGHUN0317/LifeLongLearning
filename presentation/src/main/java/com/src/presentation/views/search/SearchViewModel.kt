package com.src.presentation.views.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.src.domain.model.LectureEntity
import com.src.domain.usecase.GetLecturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getLecturesUseCase: GetLecturesUseCase
) : ViewModel() {

    val lectures: Flow<PagingData<LectureEntity>> = getLecturesUseCase.execute()
        .cachedIn(viewModelScope)
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
}