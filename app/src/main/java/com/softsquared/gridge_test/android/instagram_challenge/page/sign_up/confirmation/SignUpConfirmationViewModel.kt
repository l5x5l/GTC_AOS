package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation

import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import kotlinx.coroutines.launch

class SignUpConfirmationViewModel : BaseViewModel() {
    val userName = SignUpData.getInstance().loginId
    private val repository = UserRepository.getInstance()

    private val _signUpResult = MutableEventFlow<Int>()
    val signUpResult = _signUpResult.asEventFlow()

    fun trySignUp(){
        viewModelScope.launch(networkExceptionHandler) {
            startLoadingDialogDebounce()
            val response = repository.postSignUp(SignUpData.getInstance())
            setLoadingDialogState(isShow = false)
            if (response.code == 1000){
                response.result?.let {
                    GlobalApplication.saveJwtToken(it.jwt)
                    GlobalApplication.saveLoginId(SignUpData.getInstance().loginId)
                }
            }
            _signUpResult.emit(response.code)
        }
    }
}