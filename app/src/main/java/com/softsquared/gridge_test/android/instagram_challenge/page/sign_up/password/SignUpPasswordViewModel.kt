package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.password

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import com.softsquared.gridge_test.android.instagram_challenge.utils.checkPasswordRegex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpPasswordViewModel : BaseViewModel() {
    var password = StringWrapper("")

    private val _nextButtonState = MutableStateFlow(false)
    val nextButtonState = _nextButtonState.asStateFlow()

    fun applyToSignUpData(){
        SignUpData.getInstance().password = password.value
    }

    fun checkNextButtonState(){
        _nextButtonState.value = checkPasswordRegex(password.value)
    }
}