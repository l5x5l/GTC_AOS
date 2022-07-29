package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.auth_code

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpAuthCodeBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.name.SignUpNameActivity
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.password.SignUpPasswordActivity

class SignUpAuthCodeActivity : BaseActivity<ActivitySignUpAuthCodeBinding>(R.layout.activity_sign_up_auth_code) {

    private val viewModel : SignUpAuthCodeViewModel by lazy { ViewModelProvider(this)[SignUpAuthCodeViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setButton()
    }

    override fun setButton() {
        binding.viewSignUpAuthCode.setTextChangeCallback {
            viewModel.checkNextButtonState()
        }

        binding.tvbtnMoveBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SignUpNameActivity::class.java)
            startActivity(intent)
        }
    }
}