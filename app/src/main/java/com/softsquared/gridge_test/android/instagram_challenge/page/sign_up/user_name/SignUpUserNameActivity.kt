package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpUserNameBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation.SignUpConfirmationActivity

class SignUpUserNameActivity : BaseActivity<ActivitySignUpUserNameBinding>(R.layout.activity_sign_up_user_name) {
    private val viewModel : SignUpUserNameViewModel by lazy { ViewModelProvider(this)[SignUpUserNameViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewLoginEdittextUserName.setTextChangeCallback {

        }

        setButton()
    }

    override fun setButton() {
        binding.viewBottomArea.tvbtnLogin.setOnClickListener {
            SignUpData.clearInstance()
            finishAffinity()
        }

        binding.btnNext.setOnClickListener {
            // 먼저 조건 체크 필요
            viewModel.applyToSignUpData()
            val intent = Intent(this, SignUpConfirmationActivity::class.java)
            startActivity(intent)
        }
    }
}