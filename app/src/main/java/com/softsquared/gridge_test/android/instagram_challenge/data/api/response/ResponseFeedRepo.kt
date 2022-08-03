package com.softsquared.gridge_test.android.instagram_challenge.data.api.response

import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.CommentData
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

data class ResponseComments(val id : Int, val loginId : String, val commentText : String, val createdAt : String, val updatedAt : String) {
    companion object {
        fun toCommentData(response : ResponseComments) : CommentData {
            return CommentData(id = response.id, loginId = response.loginId, createdAt = response.createdAt, comment = response.commentText )
        }
    }
}