package com.softsquared.gridge_test.android.instagram_challenge.repository

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestCommentCreate
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestFeedCreate
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseComments
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseFeeds
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.CommentData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface.FeedRetrofitInterface
import retrofit2.HttpException

class FeedRepository private constructor() {
    companion object {
        private var instance : FeedRepository ?= null

        fun getInstance() : FeedRepository {
            if (instance == null) {
                instance = FeedRepository()
            }
            return instance!!
        }
    }

    private val retrofitImpl = GlobalApplication.sRetrofit.create(FeedRetrofitInterface::class.java)

    suspend fun getFeeds(pageIdx : Int, pageSize : Int) : List<FeedData> {
        val result = retrofitImpl.getFeeds(pageIdx = pageIdx, size = pageSize)
        if (result.isSuccessful && result.body()!!.code == 1000) {
            return result.body()!!.result?.map { ResponseFeeds.toFeedData(it) } ?: listOf()
        }
        throw HttpException(result)
    }

    suspend fun getComments(pageIdx : Int, pageSize : Int, feedId : Int) : List<CommentData> {
        val result = retrofitImpl.getComments(pageIdx = pageIdx, size = pageSize, feedId = feedId)
        if (result.isSuccessful && result.body()!!.code == 1000) {
            return result.body()!!.result?.map { ResponseComments.toCommentData(it) } ?: listOf()
        }
        throw HttpException(result)
    }

    suspend fun postCreateFeed(feedText : String, contentsUrls : ArrayList<String>) : BaseApiResponse<Nothing> {
        val result = retrofitImpl.postCreateFeed(params = RequestFeedCreate(feedText = feedText, contentsUrls = contentsUrls))
        if (result.isSuccessful) {
            return result.body()!!
        }
        throw HttpException(result)
    }

    suspend fun getUserFeeds(pageIdx : Int, pageSize : Int, loginId : String) : List<FeedData> {
        val result = retrofitImpl.getUserFeeds(pageIdx = pageIdx, size = pageSize, loginId = loginId)
        if (result.isSuccessful && result.body()!!.code == 1000) {
            return result.body()!!.result?.map { ResponseFeeds.toFeedData(it) } ?: listOf()
        }
        throw HttpException(result)
    }

    suspend fun postCreateComment(commentText : String, feedId : Int) : BaseApiResponse<Nothing> {
        val result = retrofitImpl.postComment(feedId = feedId, params = RequestCommentCreate(commentText = commentText))
        if (result.isSuccessful) {
            return result.body()!!
        }
        throw HttpException(result)
    }
}