package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleImageBinding

class SingleImageViewHolder(private val binding : ViewSingleImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl : String) {
        binding.imageUrl = imageUrl
    }
}