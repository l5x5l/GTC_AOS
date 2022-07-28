package com.softsquared.gridge_test.android.instagram_challenge.page.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel : LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewLoginEdittextId.setTextChangeCallback(viewModel::checkLoginButtonState)
        binding.viewLoginEdittextPassword.setTextChangeCallback(callback = {
            viewModel.checkLoginButtonState()
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginResult.collect{ isSuccess ->
                        if (isSuccess) {
                            Log.d("!!!!", "success")
                        } else {
                            create2ButtonDialog(
                                title = getString(R.string.message_cannot_found_account),
                                message = getString(R.string.description_cannot_founc_account, viewModel.loginId.value),
                                positiveWord = getString(R.string.do_sign_up), negativeWord = getString(R.string.try_again),
                                positiveCallback = {
                                    Log.d("!!!!", "click positive")
                                },
                                negativeCallback = {
                                    Log.d("!!!!", "click positive")
                                }
                            )
                        }
                    }
                }
            }
        }

        setButton()
    }

    override fun setButton() {
        binding.ivbtnTogglePasswordVisibility.setOnClickListener { imageView ->
            imageView as AppCompatImageView
            // 아래 callback 함수는 password 모드가 전환된 이후 실행됩니다.
            binding.viewLoginEdittextPassword.togglePasswordMode { isPasswordMode ->
                if (isPasswordMode) {
                    imageView.setImageResource(R.drawable.ic_eye_deactivate)
                } else {
                    imageView.setImageResource(R.drawable.ic_eye_activate)
                }
            }
        }
    }
}