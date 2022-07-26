package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.FEED
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleFeedBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.comment.CommentActivity
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.SingleImageAdapter
import com.softsquared.gridge_test.android.instagram_challenge.utils.getTimeDiffFromCurrent

class FeedViewHolder(private val binding : ViewSingleFeedBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.vp2Images.adapter = SingleImageAdapter()
        binding.vp2Images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvImageCount.text = binding.root.context.getString(R.string.format_count, (position + 1), binding.feedData?.imageList?.size ?: 1)
                binding.viewCountDot.changeItem(position)
            }
        })
        binding.tvbtnShowAllComment.setOnClickListener {
            val intent = Intent(binding.root.context, CommentActivity::class.java)
            intent.putExtra(FEED, binding.feedData)
            binding.root.context.startActivity(intent)
        }
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
        binding.tvCreatedAt.text = getTimeDiffFromCurrent(feedData.createAt, binding.root.context)
        binding.viewCountDot.clearAllChild()
        binding.viewCountDot.applyItemCount(feedData.imageList.size)
     }
}