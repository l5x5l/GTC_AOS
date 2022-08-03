package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.password

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpPasswordBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.birthday.SignUpBirthdayActivity
import com.softsquared.gridge_test.android.instagram_challenge.utils.closeSignUpSteps

class SignUpPasswordActivity : BaseActivity<ActivitySignUpPasswordBinding>(R.layout.activity_sign_up_password) {
    override val viewModel : SignUpPasswordViewModel by lazy { ViewModelProvider(this)[SignUpPasswordViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setButton()

        binding.viewEdittextPassword.setTextChangeCallback {
            viewModel.checkNextButtonState()
        }
    }

    override fun setButton() {
        binding.btnNext.setOnClickListener {
            viewModel.applyToSignUpData()
            val intent = Intent(this, SignUpBirthdayActivity::class.java)
            startActivity(intent)
        }

        binding.viewBottomArea.tvbtnLogin.setOnClickListener {
            closeSignUpSteps(this)
        }
    }
}