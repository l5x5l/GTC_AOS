package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestCommentCreate
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestFeedCreate
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseComments
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseFeeds
import retrofit2.Response
import retrofit2.http.*

interface FeedRetrofitInterface {
    @GET("app/feeds")
    suspend fun getFeeds(@Query("pageIndex") pageIdx : Int, @Query("size") size : Int) : Response<BaseApiResponse<ArrayList<ResponseFeeds>>>

    @GET("app/feeds/{feedId}/comments")
    suspend fun getComments(@Path("feedId") feedId : Int, @Query("pageIndex") pageIdx : Int, @Query("size") size : Int) : Response<BaseApiResponse<ArrayList<ResponseComments>>>

    @POST("app/feed")
    suspend fun postCreateFeed(@Body params : RequestFeedCreate) : Response<BaseApiResponse<Nothing>>

    @GET("app/feeds/user")
    suspend fun getUserFeeds(@Query("pageIndex") pageIdx : Int, @Query("size") size : Int, @Query("loginId") loginId : String) : Response<BaseApiResponse<ArrayList<ResponseFeeds>>>

    @POST("app/feeds/{feedId}/comment")
    suspend fun postComment(@Path("feedId") feedId: Int, @Body params : RequestCommentCreate) : Response<BaseApiResponse<Nothing>>
}