package com.softsquared.gridge_test.android.instagram_challenge.page.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.recycler.paging3.FeedPagingSource
import com.softsquared.gridge_test.android.instagram_challenge.repository.FeedRepository

class HomeViewModel : BaseViewModel() {
    private val repository = FeedRepository.getInstance()
    private val pageSize = 10

    var pagingFlow = Pager(PagingConfig(pageSize)) {
        FeedPagingSource(repository = repository, pageSize = pageSize)
    }.flow.cachedIn(viewModelScope)
}