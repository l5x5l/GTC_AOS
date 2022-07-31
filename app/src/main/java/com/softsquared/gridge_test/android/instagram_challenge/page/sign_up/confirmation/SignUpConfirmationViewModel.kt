package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData

class SignUpConfirmationViewModel : BaseViewModel() {
    val userName = SignUpData.getInstance().loginId
}