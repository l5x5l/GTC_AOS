package com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleFeedImageBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder.SingleFeedImageViewHolder

class MyPageFeedListAdapter : PagingDataAdapter<FeedData, SingleFeedImageViewHolder>(diffCallback = FeedData.diffCallback){
    override fun onBindViewHolder(holder: SingleFeedImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleFeedImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewSingleFeedImageBinding.inflate(layoutInflater, parent, false)
        return SingleFeedImageViewHolder(binding)
    }
}