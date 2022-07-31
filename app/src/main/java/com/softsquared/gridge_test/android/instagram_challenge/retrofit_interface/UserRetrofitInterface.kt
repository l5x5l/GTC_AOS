package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignUp
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserRetrofitInterface {
    @GET("app/auto-sign-in")
    suspend fun getAutoSignIn() : Response<BaseApiResponse<Nothing>>

    @POST("app/sign-in")
    suspend fun postSignIn(@Body params : RequestUserSignIn) : Response<BaseApiResponse<ResponseUserSignIn>>

    @GET("app/check-duplicate-login-id")
    suspend fun getCheckDuplicateLoginId(@Query("loginId") loginId : String) : Response<BaseApiResponse<Nothing>>

    @POST("app/sign-up")
    suspend fun postSignUp(@Body params : RequestUserSignUp) : Response<BaseApiResponse<ResponseUserSignUp>>
}