package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRetrofitInterface {
    @GET("app/auto-sign-in")
    suspend fun getAutoSignIn() : Response<BaseApiResponse<Nothing>>

    @POST("app/sign-in")
    suspend fun postSignIn(@Body params : RequestUserSignIn) : Response<BaseApiResponse<ResponseUserSignIn>>
}