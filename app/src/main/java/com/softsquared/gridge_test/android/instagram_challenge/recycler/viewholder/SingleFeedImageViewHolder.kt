package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleFeedImageBinding

class SingleFeedImageViewHolder(private val binding : ViewSingleFeedImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(feedData : FeedData){
        binding.feedData = feedData
    }
}