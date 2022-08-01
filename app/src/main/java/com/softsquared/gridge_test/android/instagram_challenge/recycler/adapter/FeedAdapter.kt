package com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleFeedBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder.FeedViewHolder

class FeedAdapter : PagingDataAdapter<FeedData, FeedViewHolder>(diffCallback = FeedData.diffCallback){
    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewSingleFeedBinding.inflate(layoutInflater, parent, false)
        return FeedViewHolder(binding)
    }
}