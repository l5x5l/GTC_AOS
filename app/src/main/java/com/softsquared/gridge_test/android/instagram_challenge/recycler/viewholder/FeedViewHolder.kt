package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleFeedBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.SingleImageAdapter

class FeedViewHolder(private val binding : ViewSingleFeedBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.vp2Images.adapter = SingleImageAdapter()
        binding.vp2Images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvImageCount.text = binding.root.context.getString(R.string.format_count, (position + 1), binding.feedData?.imageList?.size ?: 1)
            }
        })
    }

    fun bind(feedData: FeedData) {
        binding.feedData = feedData
        (binding.vp2Images.adapter as SingleImageAdapter).applyImageUrlData(feedData.imageList)
        if (feedData.imageList.size > 1) {
            binding.tvImageCount.text = binding.root.context.getString(R.string.format_count, 1, binding.feedData?.imageList?.size ?: 1)
            binding.tvImageCount.visibility = View.VISIBLE
        } else {
            binding.tvImageCount.visibility = View.GONE
        }
    }
}