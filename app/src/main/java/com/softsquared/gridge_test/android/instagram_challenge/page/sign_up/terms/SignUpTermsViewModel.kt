package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.terms

import androidx.lifecycle.ViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.TermType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpTermsViewModel : ViewModel() {
    // 해당 페이지만 예외로, nextButtonState 가 agreeAll 과 같이 사용됩니다
    private val _agreeAll = MutableStateFlow(false)
    val agreeAll = _agreeAll.asStateFlow()

    private val _agreeTermsOfUse = MutableStateFlow(false)
    val agreeTermsOfUse = _agreeTermsOfUse.asStateFlow()

    private val _agreeDataPolicy = MutableStateFlow(false)
    val agreeDataPolicy = _agreeDataPolicy.asStateFlow()

    private val _agreeLocationBasedFunction = MutableStateFlow(false)
    val agreeLocationBasedFunction = _agreeLocationBasedFunction.asStateFlow()

    fun toggleAll(){
        _agreeAll.value = !agreeAll.value
        _agreeTermsOfUse.value = agreeAll.value
        _agreeDataPolicy.value = agreeAll.value
        _agreeLocationBasedFunction.value = agreeAll.value
    }

    fun isCheckAll(){
        _agreeAll.value = (agreeTermsOfUse.value && agreeDataPolicy.value && agreeLocationBasedFunction.value)

    }

    fun toggleCheckTerm(termType: TermType){
        when (termType) {
            TermType.TERMS_OF_USE -> {
                _agreeTermsOfUse.value = !agreeTermsOfUse.value
            }
            TermType.DATA_POLICY -> {
                _agreeDataPolicy.value = !agreeDataPolicy.value
            }
            TermType.LOCATION_BASED_FUNCTION -> {
                _agreeLocationBasedFunction.value = !agreeLocationBasedFunction.value
            }
        }
    }

}