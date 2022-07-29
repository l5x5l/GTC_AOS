package com.softsquared.gridge_test.android.instagram_challenge.data.in_app

class SignUpData private constructor(var name : String, var password : String, var loginId : String, var birthDate : String, var phoneNumber : String, var isSocial : Boolean, var international_number : String) {
    companion object {
        private var instance : SignUpData ?= null

        fun getInstance() : SignUpData {
            if (instance == null){
                instance = SignUpData(name = "", password = "", loginId = "", birthDate = "", phoneNumber = "", isSocial = false, international_number = "")
            }
            return instance!!
        }

        fun clearInstance() {
            if (instance != null){
                instance = null
            }
        }
    }
}