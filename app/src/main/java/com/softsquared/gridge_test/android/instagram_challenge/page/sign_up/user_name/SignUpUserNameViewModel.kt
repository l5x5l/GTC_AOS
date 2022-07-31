package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name

import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpUserNameViewModel : BaseViewModel() {

    private val repository = UserRepository()

    private val _checkDuplicateLoginIdResult = MutableEventFlow<Int>()
    val checkDuplicateLoginIdResult = _checkDuplicateLoginIdResult.asEventFlow()

    var userName = StringWrapper("")


    fun applyToSignUpData(){
        SignUpData.getInstance().loginId = userName.value
    }

    fun tryCheckDuplicateLoginId() {
        viewModelScope.launch(Dispatchers.IO) {
            startLoadingDialogDebounce(500L)
            val result = repository.getDuplicateCheckLoginId(userName.value)
            setLoadingDialogState(false)
            _checkDuplicateLoginIdResult.emit(result.code)
        }

    }
}