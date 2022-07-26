package com.softsquared.gridge_test.android.instagram_challenge.page.login

import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import com.softsquared.gridge_test.android.instagram_challenge.utils.checkIdRegex
import com.softsquared.gridge_test.android.instagram_challenge.utils.checkPasswordRegex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    val loginId = StringWrapper("")
    val password = StringWrapper("")

    private val repository = UserRepository.getInstance()

    private val _loginButtonState = MutableStateFlow(false)
    val loginButtonState = _loginButtonState.asStateFlow()

    private val _loginResult = MutableEventFlow<Boolean>()
    val loginResult = _loginResult.asEventFlow()

    private val _kakaoLoginResult = MutableEventFlow<Int>()
    val kakaoLoginResult = _kakaoLoginResult.asEventFlow()

    private suspend fun tryLogin() {
        startLoadingDialogDebounce()
        val response = repository.postLogin(loginId = loginId.value, password = password.value)
        setLoadingDialogState(false)
        if (response.code == 1000) {
            response.result?.let {
                GlobalApplication.saveJwtToken(it.jwt)
                GlobalApplication.saveLoginId(it.loginId)
            }
        }
        _loginResult.emit(response.code == 1000)
    }

    fun checkLoginButtonState() {
        _loginButtonState.value = (loginId.value != "" && password.value != "")
    }

    fun checkIdPasswordValidation() {
        if (checkIdRegex(loginId.value) && checkPasswordRegex(password.value)) {
            viewModelScope.launch(Dispatchers.IO) {
                tryLogin()
            }
        } else {
            viewModelScope.launch {
                _loginResult.emit(false)
            }
        }
    }

    fun tryKakaoLogin(accessToken : String) {
        viewModelScope.launch {
            startLoadingDialogDebounce()
            val response = repository.postKakaoSignIn(accessToken = accessToken)
            setLoadingDialogState(false)
            if (response.code == 1000) {
                response.result?.let {
                    GlobalApplication.saveJwtToken(it.jwt)
                    GlobalApplication.saveLoginId(it.loginId)
                }
            }
            _kakaoLoginResult.emit(response.code)
        }
    }

}