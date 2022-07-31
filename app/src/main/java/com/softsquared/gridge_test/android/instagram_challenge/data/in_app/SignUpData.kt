package com.softsquared.gridge_test.android.instagram_challenge.data.in_app

import com.softsquared.gridge_test.android.instagram_challenge.data.api.request.RequestUserSignUp

class SignUpData private constructor(var name : String, var password : String, var loginId : String, var birthDate : String, var phoneNumber : String, var isSocial : Boolean, var international_number : String, var accessToken : String) {
    companion object {
        private var instance : SignUpData ?= null

        fun getInstance() : SignUpData {
            if (instance == null){
                instance = SignUpData(name = "", password = "", loginId = "", birthDate = "", phoneNumber = "", isSocial = false, international_number = "", accessToken = "")
            }
            return instance!!
        }

        fun clearInstance() {
            if (instance != null){
                instance = null
            }
        }
    }

    fun toRequestUserSignUp() : RequestUserSignUp {
        return RequestUserSignUp(realName = name, password = password, loginId = loginId, birthDate = birthDate, phoneNumber = phoneNumber)
    }
}