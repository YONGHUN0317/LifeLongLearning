package com.src.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.src.domain.model.Lecture
import com.src.domain.repository.LectureRepository

class GetPagingUseCase (private val repository: LectureRepository) {
    fun execute(): Pager<Int, Lecture> {
        return Pager(PagingConfig(pageSize = 20)) {
            repository.getLecturesPaged()
        }
    }
}