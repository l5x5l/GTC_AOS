package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpUserNameViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _checkDuplicateLoginIdResult = MutableEventFlow<Int>()
    val checkDuplicateLoginIdResult = _checkDuplicateLoginIdResult.asEventFlow()

    var userName = StringWrapper("")


    fun applyToSignUpData(){
        SignUpData.getInstance().loginId = userName.value
    }

    fun tryCheckDuplicateLoginId() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getDuplicateCheckLoginId(userName.value)
            _checkDuplicateLoginIdResult.emit(result.code)
        }

    }
}