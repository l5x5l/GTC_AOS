package com.softsquared.gridge_test.android.instagram_challenge.data.in_app

import androidx.recyclerview.widget.DiffUtil

data class CommentData(val id : Int, val comment : String,val loginId : String, val createdAt : String) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CommentData>() {
            override fun areItemsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
                return (oldItem.comment == newItem.comment && oldItem.createdAt == newItem.createdAt)
            }
        }
    }
}