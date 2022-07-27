package com.softsquared.gridge_test.android.instagram_challenge.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewLoginEditTextBinding
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper

class ViewLoginEditText(context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = ViewLoginEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    private var errorMessage = ""

    fun bind(stringWrapper: StringWrapper) {
        binding.stringWrapper = stringWrapper
    }

    fun setErrorMessage(newErrorMessage : String) {
        errorMessage = newErrorMessage
    }

    fun setHintMessage(hintMessage : String) {
        binding.etInput.hint = hintMessage
    }

    fun setEditTextEnable(isEnable : Boolean) {
        binding.etInput.isEnabled = isEnable
    }

    fun changeToErrorMode() {
        if (errorMessage.isNotEmpty()){
            binding.tvErrorMessage.visibility = View.VISIBLE
        }
    }

    fun changeToNormalMode() {
        binding.tvErrorMessage.visibility = View.GONE
    }
}