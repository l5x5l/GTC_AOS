package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.birthday

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivitySignUpBirthdayBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.terms.SignUpTermsActivity
import com.softsquared.gridge_test.android.instagram_challenge.utils.calcKoreanAge
import java.util.*

class SignUpBirthdayActivity : BaseActivity<ActivitySignUpBirthdayBinding>(R.layout.activity_sign_up_birthday) {
    override val viewModel : SignUpBirthdayViewModel by lazy { ViewModelProvider(this)[SignUpBirthdayViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setButton()
        setBirthDayText()
    }

    override fun setButton() {
        binding.btnNext.setOnClickListener {
            viewModel.applyToSignUpData()
            val intent = Intent(this, SignUpTermsActivity::class.java)
            startActivity(intent)
        }

        binding.etBirthday.setOnClickListener {
            DatePickerDialog(this,
                { _, year, month, day ->
                    viewModel.setBirthDay(year = year, month = month, day = day)
                    viewModel.checkNextButtonState()
                    setBirthDayText()
                    setAgeText()
                }, viewModel.year, viewModel.month, viewModel.day).show()
        }
    }

    private fun setBirthDayText(){
        binding.etBirthday.hint = getString(R.string.format_birthday, viewModel.year, viewModel.month, viewModel.day)
    }

    private fun setAgeText(){
        binding.tvAge.text = getString(R.string.format_age, calcKoreanAge(viewModel.year))
    }
}