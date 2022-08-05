package com.softsquared.gridge_test.android.instagram_challenge.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.page.login.LoginActivity

fun closeSignUpSteps(context: AppCompatActivity, isLogin : Boolean = false) {
    SignUpData.clearInstance()
    val intent = Intent(context, LoginActivity::class.java)
    intent.putExtra(GlobalApplication.X_ACCESS_TOKEN, isLogin)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    context.startActivity(intent)
}

fun moveToLoginPage(context : AppCompatActivity) {
    GlobalApplication.clearLoginId()
    GlobalApplication.clearJwtToken()
    val intent = Intent(context, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}