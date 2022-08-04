package com.softsquared.gridge_test.android.instagram_challenge.base_component

import android.app.Application
import android.content.SharedPreferences
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import com.softsquared.gridge_test.android.instagram_challenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GlobalApplication : Application() {
    companion object {
        const val API_URL = "https://challenge-api.gridge.co.kr/"
        const val X_ACCESS_TOKEN = "x-access-token"
        lateinit var sRetrofit: Retrofit
        lateinit var globalSharedPreferences: SharedPreferences

        fun saveJwtToken(jwtToken : String){
            globalSharedPreferences.edit().putString(X_ACCESS_TOKEN, jwtToken).commit()
        }

        fun clearJwtToken() {
            globalSharedPreferences.edit().remove(X_ACCESS_TOKEN).commit()
        }

        fun hasJwtToken() : Boolean {
            return globalSharedPreferences.contains(X_ACCESS_TOKEN)
        }

        fun saveLoginId(loginId : String){
            globalSharedPreferences.edit().putString(LOGIN_ID, loginId).commit()
        }

        fun clearLoginId() {
            globalSharedPreferences.edit().remove(LOGIN_ID).commit()
        }

        fun getLoginId() : String {
            return globalSharedPreferences.getString(LOGIN_ID, "") ?: ""
        }
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofitInstance()
        globalSharedPreferences = applicationContext.getSharedPreferences("GTC_PROBE", MODE_PRIVATE)
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
        FirebaseApp.initializeApp(baseContext)
    }

    private fun initRetrofitInstance() {
        val client : OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor())
            .build()

        //sRetrofit = Retrofit.Builder().baseUrl(API_URL).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build()
        sRetrofit = Retrofit.Builder().baseUrl(API_URL).client(client).addConverterFactory(
            GsonConverterFactory.create()).build()
    }
}