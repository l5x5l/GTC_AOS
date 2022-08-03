package com.softsquared.gridge_test.android.instagram_challenge.recycler.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.CommentData
import com.softsquared.gridge_test.android.instagram_challenge.repository.FeedRepository
import java.lang.Exception

class CommentPagingSource(private val repository : FeedRepository, private val pageSize : Int = 10, private val feedId : Int) : PagingSource<Int, CommentData>() {
    override fun getRefreshKey(state: PagingState<Int, CommentData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentData> {
        return try {
            val pageIdx = params.key ?: 0
            val response = repository.getComments(pageIdx = pageIdx, pageSize = pageSize, feedId = feedId)
            val nextKey = if (response.isEmpty()) null else pageIdx + 1
            val prevKey = if (pageIdx == 0) null else pageIdx - 1
            LoadResult.Page(data = response, prevKey = prevKey, nextKey = nextKey)
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}