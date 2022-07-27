package com.softsquared.gridge_test.android.instagram_challenge.page.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartViewModel : ViewModel() {

    private val repository = UserRepository.getInstance()

    private val _autoLoginEventFlow = MutableEventFlow<Int>()
    val autoLoginEventFlow = _autoLoginEventFlow.asEventFlow()

    fun tryGetAutoLogin(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAutoLogin()
            delay(1000L)
            _autoLoginEventFlow.emit(result.code)
        }
    }
}