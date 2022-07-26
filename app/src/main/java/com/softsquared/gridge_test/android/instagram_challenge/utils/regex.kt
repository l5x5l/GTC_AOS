package com.softsquared.gridge_test.android.instagram_challenge.utils

const val passwordRegex = "^(?=.*[@!%*#?&.])[A-Za-z0-9@!%*#?&.]{6,20}$"
const val userNameRegex = "^[a-z0-9._]{1,20}$"

fun checkPasswordRegex(password : String) : Boolean {
    return password.matches(passwordRegex.toRegex())
}

fun checkIdRegex(id : String) : Boolean {
    return (id.length in 3..20)
}

fun checkUserNameRegex(userName : String) : Boolean {
    return userName.matches(userNameRegex.toRegex())
}