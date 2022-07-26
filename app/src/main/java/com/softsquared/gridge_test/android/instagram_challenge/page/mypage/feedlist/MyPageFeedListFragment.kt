package com.softsquared.gridge_test.android.instagram_challenge.page.mypage.feedlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseFragment
import com.softsquared.gridge_test.android.instagram_challenge.databinding.FragmentMypageFeedlistBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.MyPageFeedListAdapter
import com.softsquared.gridge_test.android.instagram_challenge.recycler.item_decoration.Grid3ItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPageFeedListFragment : BaseFragment<FragmentMypageFeedlistBinding>(FragmentMypageFeedlistBinding::bind, R.layout.fragment_mypage_feedlist) {
    override val viewModel : MyPageFeedListViewModel by lazy { ViewModelProvider(this)[MyPageFeedListViewModel::class.java] }
    private var pagingJob : Job?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch{
            viewModel.pagingFlow.collectLatest { pagingData ->
                (binding.rvFeed.adapter as MyPageFeedListAdapter).submitData(pagingData)
            }
        }
    }

    private fun setRecyclerView(){
        binding.rvFeed.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvFeed.adapter = MyPageFeedListAdapter()
        binding.rvFeed.addItemDecoration(Grid3ItemDecoration())
    }

    fun refreshFeedList() {
        pagingJob?.cancel()
        pagingJob = lifecycleScope.launch {
            (binding.rvFeed.adapter as MyPageFeedListAdapter).submitData(PagingData.empty())
            viewModel.pagingFlow.collectLatest { pagingData ->
                (binding.rvFeed.adapter as MyPageFeedListAdapter).submitData(pagingData)
            }
            (binding.rvFeed.adapter as MyPageFeedListAdapter).refresh()
        }
    }
}