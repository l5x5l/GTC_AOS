package com.softsquared.gridge_test.android.instagram_challenge.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewLoginIdSelectButtonBinding

class ViewLoginIdSelectButton(context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = ViewLoginIdSelectButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.loginIdSelectButton, 0, 0).apply {
            val idType = getString(R.styleable.loginIdSelectButton_login_id_type)
            idType?.let { binding.tvIdType.text = idType }

            val isDefault = getBoolean(R.styleable.loginIdSelectButton_is_default, false)
            if (isDefault) {
                setActivate()
            } else {
                setDeactivate()
            }
        }
    }

    fun setActivate(){
        binding.tvIdType.setTextColor(ContextCompat.getColor(context, R.color.black))
        binding.lineBottom.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }

    fun setDeactivate(){
        binding.tvIdType.setTextColor(ContextCompat.getColor(context, R.color.gray600))
        binding.lineBottom.setBackgroundColor(ContextCompat.getColor(context, R.color.gray600))
    }
}