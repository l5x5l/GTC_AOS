package com.softsquared.gridge_test.android.instagram_challenge.repository

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseApiResponse
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.retrofit_interface.UserRetrofitInterface
import retrofit2.HttpException

class UserRepository {
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

}