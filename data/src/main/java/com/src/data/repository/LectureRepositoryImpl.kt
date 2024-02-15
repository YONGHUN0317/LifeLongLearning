package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.local.LecturePagingSource
import com.src.data.db.LectureDao
import com.src.domain.model.Lecture
import com.src.domain.repository.LectureRepository
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureDao: LectureDao
) : LectureRepository {
    /*override fun getAllLectures(): Single<List<Lecture>> {
        return lectureDao.getAllLectures()
    }*/

    override fun getLecturesPaged(): PagingSource<Int, Lecture> {
        return LecturePagingSource(lectureDao)
    }
}