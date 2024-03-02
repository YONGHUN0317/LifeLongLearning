package com.src.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import java.io.IOException
import javax.inject.Inject

class LecturePagingSource @Inject constructor(
    private val lectureApiService: LectureApiService
) : PagingSource<Int, LectureEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LectureEntity> {
        val pageNumber = params.key ?: 1
        val pageSize = 20

        try {
            val response = lectureApiService.getLecturesByPage(page = pageNumber, size = pageSize)
            val data = response.map(Mapper::mapLectureDataToEntity)

            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (data.isNotEmpty()) pageNumber + 1 else null

            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LectureEntity>): Int? {
        // Logic to determine which page to refresh from
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
