package com.softsquared.gridge_test.android.instagram_challenge.page.start

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityStartBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.login.LoginActivity
import kotlinx.coroutines.launch

class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override val viewModel : StartViewModel by lazy { ViewModelProvider(this)[StartViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.autoLoginEventFlow.collect {
                    if (it == 2001) {
                        showSimpleToastMessage("검증완료!")
                    } else {
                        startActivity(Intent(baseContext, LoginActivity::class.java))
                    }
                }
            }
        }

        viewModel.tryGetAutoLogin()
    }
}