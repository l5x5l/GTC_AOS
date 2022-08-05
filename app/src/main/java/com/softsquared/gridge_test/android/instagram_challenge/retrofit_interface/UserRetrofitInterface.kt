package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserKakaoSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignUp
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseMyPage
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserKakaoSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignUp
import retrofit2.Response
import retrofit2.http.*

interface UserRetrofitInterface {
    @GET("app/auto-sign-in")
    suspend fun getAutoSignIn() : Response<BaseApiResponse<Nothing>>

    @POST("app/sign-in")
    suspend fun postSignIn(@Body params : RequestUserSignIn) : Response<BaseApiResponse<ResponseUserSignIn>>

    @GET("app/check-duplicate-login-id")
    suspend fun getCheckDuplicateLoginId(@Query("loginId") loginId : String) : Response<BaseApiResponse<Nothing>>

    @POST("app/sign-up")
    suspend fun postSignUp(@Body params : RequestUserSignUp) : Response<BaseApiResponse<ResponseUserSignUp>>

    @POST("app/kakao-sign-in")
    suspend fun postKakaoSignIn(@Body params : RequestUserKakaoSignIn) : Response<BaseApiResponse<ResponseUserKakaoSignIn>>

    @GET("app/users/{loginId}/my-page")
    suspend fun getMyPage(@Path("loginId") loginId : String) : Response<BaseApiResponse<ResponseMyPage>>
}