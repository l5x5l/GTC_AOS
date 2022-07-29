package com.softsquared.gridge_test.android.instagram_challenge.custom_view

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewLoginEditTextBinding
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper

class ViewLoginEditText(context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = ViewLoginEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    private var errorMessage = ""
    private var useDefaultDeleteButton = true

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.loginEditText, 0, 0).apply {
            val hintMessage = getString(R.styleable.loginEditText_hint_message)
            hintMessage?.let { setHintMessage(hintMessage) }

            val inputType = getInteger(R.styleable.loginEditText_input_type, 1)
            binding.etInput.inputType = inputType

            val maxLength = getInteger(R.styleable.loginEditText_max_length, 8)
            binding.etInput.filters = arrayOf(InputFilter.LengthFilter(maxLength))

            useDefaultDeleteButton = getBoolean(R.styleable.loginEditText_use_default_delete_button, true)
        }

        binding.ivbtnClearText.setOnClickListener {
            binding.stringWrapper?.let {
                binding.etInput.setText("")
            }
        }
    }

    fun togglePasswordMode(callback : ((Boolean) -> Unit) ?= null){
        if ((binding.etInput.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            binding.etInput.inputType = (binding.etInput.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD.inv())
        } else if (binding.etInput.inputType == InputType.TYPE_CLASS_TEXT) {
            binding.etInput.inputType = (binding.etInput.inputType or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }
        callback?.let {
            val isPasswordMode = (binding.etInput.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD
            callback(isPasswordMode)
        }
    }

    fun bind(stringWrapper: StringWrapper) {
        binding.stringWrapper = stringWrapper
    }

    fun setTextChangeCallback(callback : () -> Unit){
        binding.etInput.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotEmpty() && useDefaultDeleteButton){
                    binding.ivbtnClearText.visibility = View.VISIBLE
                } else {
                    binding.ivbtnClearText.visibility = View.GONE
                }
                callback()
            }
        })
    }

    fun setErrorMessage(newErrorMessage : String) {
        errorMessage = newErrorMessage
    }

    private fun setHintMessage(hintMessage : String) {
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