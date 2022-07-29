package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.birthday

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpBirthdayBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.terms.SignUpTermsActivity

class SignUpBirthdayActivity : BaseActivity<ActivitySignUpBirthdayBinding>(R.layout.activity_sign_up_birthday) {
    private val viewModel : SignUpBirthdayViewModel by lazy { ViewModelProvider(this)[SignUpBirthdayViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setButton()
    }

    override fun setButton() {
        binding.btnNext.setOnClickListener {
            viewModel.applyToSignUpData()
            val intent = Intent(this, SignUpTermsActivity::class.java)
            startActivity(intent)
        }
    }
}