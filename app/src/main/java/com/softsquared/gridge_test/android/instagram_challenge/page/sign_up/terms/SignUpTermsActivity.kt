package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.terms

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpTermsBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name.SignUpUserNameActivity

class SignUpTermsActivity : BaseActivity<ActivitySignUpTermsBinding>(R.layout.activity_sign_up_terms) {
    private val viewModel : SignUpTermsViewModel by lazy { ViewModelProvider(this)[SignUpTermsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setButton()
    }

    override fun setButton() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SignUpUserNameActivity::class.java)
            startActivity(intent)
        }
    }
}