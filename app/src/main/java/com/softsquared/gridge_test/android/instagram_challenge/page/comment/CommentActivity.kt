package com.softsquared.gridge_test.android.instagram_challenge.page.comment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.FEED
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.FeedData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityCommentBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.CommentAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommentActivity : BaseActivity<ActivityCommentBinding>(R.layout.activity_comment) {
    override val viewModel: CommentViewModel by lazy { ViewModelProvider(this)[CommentViewModel::class.java] }
    private var pagingJob : Job?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this


        val feed = intent.getSerializableExtra(FEED) as FeedData
        if (feed.id== -1) {
            showSimpleToastMessage(getString(R.string.message_not_exist_feed))
            finish()
        }
        viewModel.setFeedId(feed.id)

        binding.viewCommentFeed.feed = feed

        setRecyclerView()
        startPagingLoad()
        setButton()
    }

    override fun setRecyclerView() {
        binding.rvComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvComment.adapter = CommentAdapter()
    }

    override fun setButton() {
        binding.ivbtnBack.setOnClickListener {
            refreshList()
        }
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

    private fun refreshList() {
        pagingJob?.cancel()
        startPagingLoad()
    }
}