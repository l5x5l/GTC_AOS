package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.name

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpNameViewModel : ViewModel() {
    val name = StringWrapper("")

    private val _nextButtonState = MutableStateFlow(false)
    val nextButtonState = _nextButtonState.asStateFlow()

    fun checkNextButtonState() {
        _nextButtonState.value = name.value.isNotEmpty()
    }

    fun applyToSignUpData(){
        SignUpData.getInstance().name = name.value
    }
}