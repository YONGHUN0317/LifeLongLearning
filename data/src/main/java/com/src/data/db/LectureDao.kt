package com.src.data.db

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface LectureDao {
    @Query("SELECT * FROM lectureInfo")
    fun getAllLectures(): Observable<List<LectureInfo>>
}