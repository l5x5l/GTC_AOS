package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.base

import android.content.Intent
import android.os.Bundle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id.SignUpIdActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setButton()
    }

    override fun setButton() {
        binding.tvbtnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpIdActivity::class.java)
            startActivity(intent)
        }
    }
}