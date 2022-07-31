package com.softsquared.gridge_test.android.instagram_challenge.page.start

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.GlobalApplication
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityStartBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.login.LoginActivity
import com.softsquared.gridge_test.android.instagram_challenge.page.main.MainActivity
import kotlinx.coroutines.launch

class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override val viewModel : StartViewModel by lazy { ViewModelProvider(this)[StartViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.autoLoginEventFlow.collect {
                    when (it) {
                        1001 -> {
                            startActivity(Intent(baseContext, MainActivity::class.java))
                            //showSimpleToastMessage("검증완료!")
                        }
                        3001 -> {
                            GlobalApplication.clearJwtToken()
                            showSimpleToastMessage(getString(R.string.message_expire_jwt_token))
                            startActivity(Intent(baseContext, LoginActivity::class.java))
                        }
                        else -> {
                            GlobalApplication.clearJwtToken()
                            startActivity(Intent(baseContext, LoginActivity::class.java))
                        }
                    }
                }
            }
        }

        viewModel.tryGetAutoLogin()
    }
}