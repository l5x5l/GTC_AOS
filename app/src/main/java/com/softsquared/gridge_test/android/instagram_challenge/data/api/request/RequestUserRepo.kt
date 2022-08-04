package com.softsquared.gridge_test.android.instagram_challenge.data.api.request

data class RequestUserSignUp(val realName : String, val password : String, val loginId : String, val birthDate : String, val phoneNumber : String)

data class RequestUserSignIn(val loginId : String, val password : String)

data class RequestUserKakaoSignUp(val accessToken : String, val realName : String, val birthDate : String, val loginId : String, val phoneNumber : String)

data class RequestUserKakaoSignIn(val accessToken : String)