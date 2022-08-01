package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseFeeds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedRetrofitInterface {
    @GET("app/feeds")
    suspend fun getFeeds(@Query("pageIndex") pageIdx : Int, @Query("size") size : Int) : Response<BaseApiResponse<ArrayList<ResponseFeeds>>>
}