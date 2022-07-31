package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.SignUpData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpUserNameBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.confirmation.SignUpConfirmationActivity
import com.softsquared.gridge_test.android.instagram_challenge.utils.checkUserNameRegex
import kotlinx.coroutines.launch

class SignUpUserNameActivity : BaseActivity<ActivitySignUpUserNameBinding>(R.layout.activity_sign_up_user_name) {
    override val viewModel : SignUpUserNameViewModel by lazy { ViewModelProvider(this)[SignUpUserNameViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewLoginEdittextUserName.setTextChangeCallback {
            if (binding.ivPass.visibility == View.VISIBLE) {
                binding.ivPass.visibility = View.GONE
                binding.ivbtnClearText.visibility = View.VISIBLE
            }
            binding.viewLoginEdittextUserName.changeToNormalMode()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.checkDuplicateLoginIdResult.collect { resultCode ->
                    when (resultCode) {
                        1000 -> {
                            binding.ivPass.visibility = View.VISIBLE
                            binding.ivbtnClearText.visibility = View.GONE
                            viewModel.applyToSignUpData()
                            val intent = Intent(baseContext, SignUpConfirmationActivity::class.java)
                            startActivity(intent)
                        }
                        2230 -> {
                            binding.viewLoginEdittextUserName.setErrorMessage(getString(R.string.message_duplicate_user_name, viewModel.userName.value))
                            binding.viewLoginEdittextUserName.changeToErrorMode()
                        }
                        else -> {

                        }
                    }
                } // checkDuplicateLoginResult.collect
            } // repeatOnLifecycle
        } // lifeCycleScope

        setButton()
    }

    override fun setButton() {
        binding.viewBottomArea.tvbtnLogin.setOnClickListener {
            SignUpData.clearInstance()
            finishAffinity()
        }

        // 다음 버튼을 클릭할 시 먼저 사용자 이름 조건을 체크하고, 해당 조건에 부합하는 경우 중복확인 api 를 호출합니다.
        binding.btnNext.setOnClickListener {
            if (!checkUserNameRegex(viewModel.userName.value)) {
                binding.viewLoginEdittextUserName.setErrorMessage(getString(R.string.message_not_match_to_user_name_format))
                binding.viewLoginEdittextUserName.changeToErrorMode()
            } else {
                viewModel.tryCheckDuplicateLoginId()
            }
        }

        binding.ivbtnClearText.setOnClickListener {
            binding.viewLoginEdittextUserName.clearText()
        }
    }
}