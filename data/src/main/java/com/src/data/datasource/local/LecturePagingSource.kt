package com.src.data.datasource.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.src.data.db.LectureDao
import com.src.domain.model.Lecture
import kotlinx.coroutines.flow.first
import java.io.IOException

class LecturePagingSource(
    private val lectureDao: LectureDao
) : PagingSource<Int, Lecture>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Lecture> {
        return try {
            val currentPage = params.key ?: 1
            val lectureList = lectureDao.getAllLectures().first()
            LoadResult.Page(
                data = lectureList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (lectureList.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Lecture>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
