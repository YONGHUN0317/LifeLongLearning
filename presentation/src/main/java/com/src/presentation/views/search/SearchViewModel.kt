package com.src.presentation.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.src.domain.model.Lecture
import com.src.domain.usecase.GetLecturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getLecturesUseCase: GetLecturesUseCase
) : ViewModel() {

    val lectures: Flow<PagingData<Lecture>> = getLecturesUseCase.execute()
        .cachedIn(viewModelScope)

}