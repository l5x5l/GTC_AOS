package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id.phone_number

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpIdPhoneViewModel : ViewModel() {
    private val _nextButtonState = MutableStateFlow(false)
    val nextButtonState = _nextButtonState.asStateFlow()

    var phoneNumber = ""
    var internationalNumber = "KR +82"

    fun checkNextButtonState(){
        _nextButtonState.value = (phoneNumber.length in 1..11)
    }

    fun applyToSignUpData(){
        val signUpData = SignUpData.getInstance()
        signUpData.phoneNumber = phoneNumber
        signUpData.international_number = internationalNumber.split(" ")[1]
    }
}