package com.softsquared.gridge_test.android.instagram_challenge.base_component

import androidx.databinding.BindingAdapter
import com.softsquared.gridge_test.android.instagram_challenge.custom_component.ViewLoginEditText
import com.softsquared.gridge_test.android.instagram_challenge.data.StringWrapper

object BindingAdapters {
//    @JvmStatic
//    @BindingAdapter("innerText")
//    fun setInnerText(view : ViewEditText, textWrapper: StringWrapper) {
//        view.bind(textWrapper)
//    }
    @JvmStatic
    @BindingAdapter("innerText")
    fun setInnerText(view : ViewLoginEditText, stringWrapper: StringWrapper) {
        view.bind(stringWrapper)
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun setErrorMessage(view : ViewLoginEditText, errorString : String) {
        view.setErrorMessage(errorString)
    }
}