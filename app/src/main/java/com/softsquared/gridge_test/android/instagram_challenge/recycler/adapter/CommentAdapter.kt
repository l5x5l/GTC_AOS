package com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.CommentData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewCommentBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder.CommentViewHolder

class CommentAdapter : PagingDataAdapter<CommentData, CommentViewHolder>(diffCallback = CommentData.diffCallback) {
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewCommentBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(binding)
    }
}