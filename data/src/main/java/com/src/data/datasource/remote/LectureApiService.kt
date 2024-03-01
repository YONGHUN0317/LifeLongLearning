package com.src.data.datasource.remote

import com.src.data.model.LectureData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface LectureApiService {
    @GET("/lifelonglearning/lecture_info")
    suspend fun getAllLectures(): List<LectureData>
}