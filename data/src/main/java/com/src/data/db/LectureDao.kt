package com.src.data.db

import androidx.room.Dao
import com.src.domain.model.Lecture
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET

@Dao
interface LectureDao {
    @GET("AllLectures")
    fun getAllLectures(): Flow<List<Lecture>>
}