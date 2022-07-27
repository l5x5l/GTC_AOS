package com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserRetrofitInterface {
    @GET("app/auto-sign-in")
    suspend fun getAutoSignIn() : Response<BaseApiResponse<Nothing>>
}