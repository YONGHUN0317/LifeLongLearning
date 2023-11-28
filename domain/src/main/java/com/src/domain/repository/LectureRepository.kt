package com.src.domain.repository

import androidx.paging.PagingSource
import com.src.domain.model.Lecture

interface LectureRepository {
    //fun getAllLectures(): Single<List<Lecture>>

    fun getLecturesPaged(): PagingSource<Int, Lecture>
}