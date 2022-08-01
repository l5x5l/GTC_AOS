package com.softsquared.gridge_test.android.instagram_challenge.repository

import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseFeeds
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
}