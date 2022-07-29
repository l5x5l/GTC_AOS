package com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.id.phone_number

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseFragment
import com.softsquared.gridge_test.android.instagram_challenge.databinding.FragmentSignUpIdPhoneBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.sign_up.auth_code.SignUpAuthCodeActivity

class SignUpIdPhoneFragment : BaseFragment<FragmentSignUpIdPhoneBinding>(FragmentSignUpIdPhoneBinding::bind, R.layout.fragment_sign_up_id_phone) {
    private val viewModel : SignUpIdPhoneViewModel by lazy { ViewModelProvider(this)[SignUpIdPhoneViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setEditText()
    }

    private fun setEditText(){
        binding.etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotEmpty()) {
                    binding.ivbtnClearText.visibility = View.VISIBLE
                } else {
                    binding.ivbtnClearText.visibility = View.GONE
                }
                viewModel.checkNextButtonState()
            }
        })

        binding.btnNext.setOnClickListener {
            viewModel.applyToSignUpData()
            val intent = Intent(activity, SignUpAuthCodeActivity::class.java)
            startActivity(intent)
        }

        binding.ivbtnClearText.setOnClickListener {
            binding.etPhone.setText("")
        }
    }
}