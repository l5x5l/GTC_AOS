package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.terms

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.TermType
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpTermsBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.user_name.SignUpUserNameActivity

class SignUpTermsActivity : BaseActivity<ActivitySignUpTermsBinding>(R.layout.activity_sign_up_terms) {
    override val viewModel : SignUpTermsViewModel by lazy { ViewModelProvider(this)[SignUpTermsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setButton()
        setCheckBox()
    }

    override fun setButton() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SignUpUserNameActivity::class.java)
            startActivity(intent)
        }

        binding.tvbtnSeeMoreTermsOfUse.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gridgetest.oopy.io")))
        }

        binding.tvbtnSeeMoreDataPolicy.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gridgetest.oopy.io")))
        }

        binding.tvbtnSeeMoreLocationBasedFunction.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gridgetest.oopy.io")))
        }

    }

    private fun setCheckBox(){
        binding.cbAgreeAll.setOnClickListener {
            viewModel.toggleAll()
            invalidateCheckBoxes()
        }

        binding.cbTermsOfUse.setOnClickListener {
            viewModel.toggleCheckTerm(TermType.TERMS_OF_USE)
            viewModel.isCheckAll()
            invalidateCheckBoxes()
        }

        binding.cbDataPolicy.setOnClickListener {
            viewModel.toggleCheckTerm(TermType.DATA_POLICY)
            viewModel.isCheckAll()
            invalidateCheckBoxes()
        }

        binding.cbLocationBasedFunction.setOnClickListener {
            viewModel.toggleCheckTerm(TermType.LOCATION_BASED_FUNCTION)
            viewModel.isCheckAll()
            invalidateCheckBoxes()
        }
    }

    private fun invalidateCheckBoxes(){
        binding.cbTermsOfUse.invalidate()
        binding.cbDataPolicy.invalidate()
        binding.cbLocationBasedFunction.invalidate()
        binding.cbAgreeAll.invalidate()
    }
}