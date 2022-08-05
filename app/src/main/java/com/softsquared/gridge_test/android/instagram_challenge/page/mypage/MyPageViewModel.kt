package com.softsquared.gridge_test.android.instagram_challenge.page.mypage

import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.api.response.ResponseMyPage
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.MyPageData
import com.softsquared.gridge_test.android.instagram_challenge.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : BaseViewModel() {
    private val repository = UserRepository.getInstance()

    private val _myPageData = MutableStateFlow(MyPageData())
    val myPageData = _myPageData.asStateFlow()

    private val _loadMyPageDataResult = MutableEventFlow<Int>()
    val loadMyPageDataResult = _loadMyPageDataResult.asEventFlow()

    fun tryGetMyPageData(){
        viewModelScope.launch(networkExceptionHandler + Dispatchers.IO) {
            startLoadingDialogDebounce()
            val response = repository.getMyPage(GlobalApplication.getLoginId())
            setLoadingDialogState(isShow = false)
            if (response.code == 1000) {
                _myPageData.value = ResponseMyPage.toMyPageData(response.result!!)
            }
            _loadMyPageDataResult.emit(response.code)
        }
    }
}