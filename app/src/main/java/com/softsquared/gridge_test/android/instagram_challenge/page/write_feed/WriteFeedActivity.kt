package com.softsquared.gridge_test.android.instagram_challenge.page.write_feed

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.IMAGE_URL
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityWriteFeedBinding
import kotlinx.coroutines.launch

class WriteFeedActivity : BaseActivity<ActivityWriteFeedBinding>(R.layout.activity_write_feed) {
    override val viewModel: WriteFeedViewModel by lazy { ViewModelProvider(this)[WriteFeedViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val imageUrl = intent.getStringExtra(IMAGE_URL)
        imageUrl?.let {
            viewModel.changeImageUrl(it)
            Glide.with(this).load(it).into(binding.ivImage)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uploadFeedResult.collect { resultCode ->
                    if (resultCode == 1000) {
                        val intent = Intent(this@WriteFeedActivity, BaseActivity::class.java)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
            }
        }

        setButton()
        setEdittext()
    }

    override fun setButton() {
        binding.ivbtnBack.setOnClickListener {
            finish()
        }
    }

    private fun setEdittext(){
        binding.etContent.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                binding.tvTitle.text = getString(R.string.phrases)
                binding.tvbtnConfirmation.visibility = View.VISIBLE
                binding.tvbtnShare.visibility = View.GONE
                binding.viewFilter.visibility = View.VISIBLE
            } else {
                binding.tvTitle.text = getString(R.string.new_feed)
                binding.tvbtnConfirmation.visibility = View.GONE
                binding.tvbtnShare.visibility = View.VISIBLE
                binding.viewFilter.visibility = View.GONE
            }
        }
    }

}