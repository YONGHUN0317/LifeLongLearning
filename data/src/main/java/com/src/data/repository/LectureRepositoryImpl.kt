package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.db.LectureDao
import com.src.domain.model.Lecture
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDao: LectureDao
) : LectureRepository {
    override fun getAllLectures(): Flow<List<Lecture>> {
        return lectureDao.getAllLectures()
    }

    override fun getLecturesPaged(): PagingSource<Int, Lecture> {
        return LecturePagingSource(lectureDao)
    }
}