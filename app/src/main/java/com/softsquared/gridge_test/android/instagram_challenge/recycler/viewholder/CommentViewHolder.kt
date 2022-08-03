package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.CommentData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewCommentBinding

class CommentViewHolder(private val binding : ViewCommentBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(commentData: CommentData) {
        binding.comment = commentData
    }
}