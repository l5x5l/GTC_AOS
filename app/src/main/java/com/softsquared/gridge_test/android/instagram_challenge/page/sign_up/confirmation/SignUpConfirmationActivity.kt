package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpConfirmationBinding
import com.softsquared.gridge_test.android.instagram_challenge.utils.closeSignUpSteps
import kotlinx.coroutines.launch

class SignUpConfirmationActivity : BaseActivity<ActivitySignUpConfirmationBinding>(R.layout.activity_sign_up_confirmation){
    override val viewModel : SignUpConfirmationViewModel by lazy { ViewModelProvider(this)[SignUpConfirmationViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpResult.collect { resultCode ->
                    when (resultCode) {
                        1000 -> {
                            closeSignUpSteps(this@SignUpConfirmationActivity, isLogin = true)
                        }
                        else -> {

                        }
                    }
                }
            }
        }

        setButton()
    }

    override fun setButton() {
        binding.tvbtnLogin.setOnClickListener {
            closeSignUpSteps(this)
        }

        binding.btnSignUp.setOnClickListener {
            viewModel.trySignUp()
        }
    }
}