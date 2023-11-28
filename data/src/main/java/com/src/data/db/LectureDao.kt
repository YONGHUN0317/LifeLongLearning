package com.src.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.src.domain.model.Lecture
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

@Dao
interface LectureDao {
    @GET("AllLectures")
    fun getAllLectures(): Single<List<Lecture>>
}