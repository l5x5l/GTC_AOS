package com.softsquared.gridge_test.android.instagram_challenge.base_component

import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication.Companion.X_ACCESS_TOKEN
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication.Companion.globalSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class XAccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder : Request.Builder = chain.request().newBuilder()
        val jwtToken : String ?= globalSharedPreferences.getString(X_ACCESS_TOKEN, null)
        jwtToken?.let { builder.addHeader("x-access-token", jwtToken) }
        return chain.proceed(builder.build())
    }
}