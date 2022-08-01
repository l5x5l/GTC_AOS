package com.softsquared.gridge_test.android.instagram_challenge.data.api.response

import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData

data class ResponseContent(val contentsId : Int, val contentsUrl : String, val createdAt : String, val updatedAt : String)

data class ResponseFeeds(val feedId : Int, val feedLoginId : String, val feedText : String,
                         val feedCreatedAt : String, val feedUpdatedAt :String, val feedCommentCount : Int, val contentsList : ArrayList<ResponseContent>) {
    companion object {
        fun toFeedData(response : ResponseFeeds) : FeedData {
            return FeedData(id = response.feedId, loginId = response.feedLoginId, text = response.feedText,
                createAt = response.feedCreatedAt, commentCount = response.feedCommentCount,
                imageList = response.contentsList.map { it.contentsUrl })
        }
    }
}