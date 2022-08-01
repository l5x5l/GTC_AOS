package com.softsquared.gridge_test.android.instagram_challenge.page.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseFragment
import com.softsquared.gridge_test.android.instagram_challenge.databinding.FragmentHomeBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.FeedAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private var pagingJob : Job ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()


        binding.layoutRefresh.setOnRefreshListener {
            refreshList()
        }
        startPagingLoad()
    }

    private fun startPagingLoad(){
        pagingJob = lifecycleScope.launch {
            (binding.rvFeed.adapter as FeedAdapter).submitData(PagingData.empty())
            viewModel.pagingFlow.collectLatest { pagingData ->
                withContext(Dispatchers.Default){
                    binding.layoutRefresh.isRefreshing = false
                }
                (binding.rvFeed.adapter as FeedAdapter).submitData(pagingData)
            }
        }
    }

    private fun refreshList() {
        pagingJob?.cancel()
        startPagingLoad()
    }

    private fun setRecyclerView() {
        binding.rvFeed.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFeed.adapter = FeedAdapter()
    }
}