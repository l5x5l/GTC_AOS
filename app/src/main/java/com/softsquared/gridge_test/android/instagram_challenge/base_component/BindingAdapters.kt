package com.softsquared.gridge_test.android.instagram_challenge.base_component

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.softsquared.gridge_test.android.instagram_challenge.custom_view.ViewLoginEditText
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("innerText")
    fun setInnerText(view : ViewLoginEditText, stringWrapper: StringWrapper) {
        view.bind(stringWrapper)
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun setErrorMessage(view : ViewLoginEditText, errorString : String = "") {
        view.setErrorMessage(errorString)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageByUrl(view : AppCompatImageView, imageUrl : String ?= null) {
        imageUrl?.let {
            Glide.with(view).load(it).into(view)
        }
    }
}