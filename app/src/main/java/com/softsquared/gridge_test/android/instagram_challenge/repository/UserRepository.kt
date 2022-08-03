package com.softsquared.gridge_test.android.instagram_challenge.repository

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignIn
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseUserSignUp
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface.UserRetrofitInterface
import retrofit2.HttpException

class UserRepository private constructor() {
    companion object {
        private var instance : UserRepository ?= null

        fun getInstance() : UserRepository {
            if (instance == null) {
                instance = UserRepository()
            }
            return instance!!
        }
    }

    private val retrofitImpl = GlobalApplication.sRetrofit.create(UserRetrofitInterface::class.java)

    suspend fun getAutoLogin() : BaseApiResponse<Nothing> {
        val result = retrofitImpl.getAutoSignIn()
        if (result.isSuccessful) {
            return result.body()!!
        }
        throw HttpException(result)
    }

    suspend fun postLogin(loginId : String, password : String) : BaseApiResponse<ResponseUserSignIn> {
        val result = retrofitImpl.postSignIn(RequestUserSignIn(loginId = loginId, password = password))
        if (result.isSuccessful){
            return result.body()!!
        }
        throw HttpException(result)
    }

    suspend fun getDuplicateCheckLoginId(loginId : String) : BaseApiResponse<Nothing> {
        val result = retrofitImpl.getCheckDuplicateLoginId(loginId = loginId)
        if (result.isSuccessful) {
            return result.body()!!
        }
        throw HttpException(result)
    }

    suspend fun postSignUp(signUpData : SignUpData) : BaseApiResponse<ResponseUserSignUp> {
        val result = retrofitImpl.postSignUp(params = signUpData.toRequestUserSignUp())
        if (result.isSuccessful){
            return result.body()!!
        }
        throw HttpException(result)
    }

}