package com.softsquared.gridge_test.android.instagram_challenge.page.comment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.FEED
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityCommentBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.CommentAdapter
import com.softsquared.gridge_test.android.instagram_challenge.utils.moveToLoginPage
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommentActivity : BaseActivity<ActivityCommentBinding>(R.layout.activity_comment) {
    override val viewModel: CommentViewModel by lazy { ViewModelProvider(this)[CommentViewModel::class.java] }
    private var pagingJob : Job?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postCommentResult.collect{ resultCode ->
                    when (resultCode) {
                        1000 -> {
                            binding.etComment.setText("")
                            (binding.rvComment.adapter as CommentAdapter).refresh()
                        }
                        3001 -> {
                            moveToLoginPage(this@CommentActivity)
                        }
                    }
                }
            }
        }

        val feed = intent.getSerializableExtra(FEED) as FeedData?
        if (feed == null) {
            showSimpleToastMessage(getString(R.string.message_not_exist_feed))
            finish()
        }
        viewModel.setFeedId(feed!!.id)

        binding.viewCommentFeed.feed = feed

        setRecyclerView()
        startPagingLoad()
        setButton()
        setEdittext()
    }

    override fun setRecyclerView() {
        binding.rvComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvComment.adapter = CommentAdapter()
    }

    override fun setButton() {
        binding.ivbtnBack.setOnClickListener {
            finish()
        }
    }

    private fun setEdittext(){
        binding.etComment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                binding.tvbtnPostComment.isEnabled = (p0.toString().isNotEmpty())
            }
        })
    }

    // paging3 사용하는 부분 중복코드가 많다
    private fun startPagingLoad(){
        pagingJob = lifecycleScope.launch {
            (binding.rvComment.adapter as CommentAdapter).submitData(PagingData.empty())
            viewModel.pagingFlow.collectLatest { pagingData ->
                (binding.rvComment.adapter as CommentAdapter).submitData(pagingData)
            }
        }
    }
}