package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper

class SignUpUserNameViewModel : ViewModel() {
    var userName = StringWrapper("")

    fun applyToSignUpData(){
        SignUpData.getInstance().loginId = userName.value
    }
}