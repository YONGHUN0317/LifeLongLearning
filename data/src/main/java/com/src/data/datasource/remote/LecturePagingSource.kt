package com.src.data.datasource.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.src.data.mapper.Mapper
import com.src.data.model.LectureData
import com.src.domain.model.LectureEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LecturePagingSource @Inject constructor(
    private val lectureApiService: LectureApiService
) : PagingSource<Int, LectureEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LectureEntity> {
        val pageNumber = params.key ?: 1
        try {
            val dataList = lectureApiService.getAllLectures()

            Log.d("LecturePagingSource", dataList.toString())

            // Map dataList to List<LectureEntity>
            val response = dataList.map { data ->
                Mapper.mapLectureDataToEntity(data)
            }

            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

            return LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            Log.d("LecturePagingSource1", exception.toString())
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            Log.d("LecturePagingSource2", exception.toString())
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, LectureEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
