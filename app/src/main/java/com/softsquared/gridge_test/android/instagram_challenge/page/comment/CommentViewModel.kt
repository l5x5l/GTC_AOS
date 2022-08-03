package com.softsquared.gridge_test.android.instagram_challenge.page.comment

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.recycler.paging3.CommentPagingSource
import com.softsquared.gridge_test.android.instagram_challenge.repository.FeedRepository

class CommentViewModel : BaseViewModel() {
    private val repository = FeedRepository.getInstance()
    private val pageSize = 10
    private var feedId = 0

    fun setFeedId(newFeedId : Int){
        feedId = newFeedId
    }

    var pagingFlow = Pager(PagingConfig(10)) {
        CommentPagingSource(repository = repository, pageSize = pageSize, feedId = feedId)
    }.flow.cachedIn(viewModelScope)
}