package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpIdBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id.email.SignUpIdEmailFragment
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id.phone_number.SignUpIdPhoneFragment

class SignUpIdActivity : BaseActivity<ActivitySignUpIdBinding>(R.layout.activity_sign_up_id) {

    override val viewModel: BaseViewModel? = null
    private val phoneFragment : SignUpIdPhoneFragment by lazy { SignUpIdPhoneFragment() }
    private val emailFragment : SignUpIdEmailFragment by lazy { SignUpIdEmailFragment() }

    private lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, emailFragment).hide(emailFragment).add(binding.layoutFragment.id, phoneFragment).commit()
        currentFragment = phoneFragment

        setButton()
    }

    override fun setButton() {
        binding.viewbtnUsePhone.setOnClickListener {
            if (currentFragment != phoneFragment) {
                supportFragmentManager.beginTransaction().hide(currentFragment).show(phoneFragment).commit()
                currentFragment = phoneFragment
                binding.viewbtnUsePhone.setActivate()
                binding.viewbtnUseEmail.setDeactivate()
            }
        }

        binding.viewbtnUseEmail.setOnClickListener {
            if (currentFragment != emailFragment){
                supportFragmentManager.beginTransaction().hide(currentFragment).show(emailFragment).commit()
                currentFragment = emailFragment
                binding.viewbtnUsePhone.setDeactivate()
                binding.viewbtnUseEmail.setActivate()
            }
        }

        binding.viewBottomArea.tvbtnLogin.setOnClickListener {
            finishAffinity()
        }

        binding.ivbtnBack.setOnClickListener {
            finish()
        }
    }
}