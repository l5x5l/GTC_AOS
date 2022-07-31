package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.birthday

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.utils.checkAvailableDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class SignUpBirthdayViewModel : BaseViewModel() {

    private val calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    private val _nextButtonState = MutableStateFlow(false)
    val nextButtonState = _nextButtonState.asStateFlow()

    fun applyToSignUpData(){
        SignUpData.getInstance().birthDate = year.toString() + "." + String.format("%02d", month + 1) + "." + String.format("%02d", day)
    }

    fun setBirthDay(year : Int, month : Int, day : Int){
        this.year = year
        this.month = month
        this.day = day
    }

    fun checkNextButtonState(){
        _nextButtonState.value = checkAvailableDate(birthYear = year, birthMonth = month + 1, birthDay = day)
    }
}