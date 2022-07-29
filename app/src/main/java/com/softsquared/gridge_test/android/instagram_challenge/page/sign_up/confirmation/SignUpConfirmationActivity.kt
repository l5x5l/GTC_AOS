package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpConfirmationBinding

class SignUpConfirmationActivity : BaseActivity<ActivitySignUpConfirmationBinding>(R.layout.activity_sign_up_confirmation){
    private val viewModel : SignUpConfirmationViewModel by lazy { ViewModelProvider(this)[SignUpConfirmationViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setButton()
    }

    override fun setButton() {
        binding.tvbtnLogin.setOnClickListener {
            SignUpData.clearInstance()
            finishAffinity()
        }

        binding.btnSignUp.setOnClickListener {

        }
    }
}