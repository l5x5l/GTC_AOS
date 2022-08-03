package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.name

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpNameBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.password.SignUpPasswordActivity
import com.softsquared.gridge_test.android.instagram_challenge.utils.closeSignUpSteps

class SignUpNameActivity : BaseActivity<ActivitySignUpNameBinding>(R.layout.activity_sign_up_name) {

    override val viewModel : SignUpNameViewModel by lazy { ViewModelProvider(this)[SignUpNameViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewLoginEdittextName.setTextChangeCallback {
            viewModel.checkNextButtonState()
        }

        binding.viewBottomArea.tvbtnLogin.setOnClickListener {
            closeSignUpSteps(this)
        }

        binding.btnNext.setOnClickListener {
            viewModel.applyToSignUpData()
            val intent = Intent(this, SignUpPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}