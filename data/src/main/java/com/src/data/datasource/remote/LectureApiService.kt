package com.src.data.datasource.remote

import com.src.data.model.LectureData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface LectureApiService {
    @GET("/lifelonglearning/lecture_info")
    suspend fun getLecturesByPage(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<LectureData>


    @GET("/lifelonglearning/lecture_info")
    suspend fun getSearch(
        @Query("lctreNm") lctreNm: String,
    ): List<LectureData>
}