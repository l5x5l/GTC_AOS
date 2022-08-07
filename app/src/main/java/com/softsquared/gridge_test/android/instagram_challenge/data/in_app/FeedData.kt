package com.softsquared.gridge_test.android.instagram_challenge.data.in_app

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class FeedData(val id : Int, val loginId : String, val text : String, val createAt : String, val commentCount : Int, val imageList: List<String>) : Serializable {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FeedData>() {
            override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
                return (oldItem.text == newItem.text && oldItem.commentCount == newItem.commentCount)
            }
        }
    }
}