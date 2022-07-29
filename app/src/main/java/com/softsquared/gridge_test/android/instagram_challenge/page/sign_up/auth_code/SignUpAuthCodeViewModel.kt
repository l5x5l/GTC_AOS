package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.auth_code

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpAuthCodeViewModel : ViewModel() {
    var phoneNumber = SignUpData.getInstance().phoneNumber
    var internationalNumber = SignUpData.getInstance().international_number

    private val _nextButtonState = MutableStateFlow(false)
    val nextButtonState = _nextButtonState.asStateFlow()

    var authCode = StringWrapper("")

    fun checkNextButtonState(){
        _nextButtonState.value = authCode.value.length == 6
    }
}