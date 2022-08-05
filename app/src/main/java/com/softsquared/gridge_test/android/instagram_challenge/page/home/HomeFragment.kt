package com.softsquared.gridge_test.android.instagram_challenge.page.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseFragment
import com.softsquared.gridge_test.android.instagram_challenge.databinding.FragmentHomeBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.image_picker.ImagePickerActivity
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.FeedAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private var pagingJob : Job ?= null
    private lateinit var feedWriteResult : ActivityResultLauncher<Intent>

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            val intent = Intent(activity, ImagePickerActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setButton()
        setRecyclerView()

        feedWriteResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                (binding.rvFeed.adapter as FeedAdapter).refresh()
            }
        }

        binding.layoutRefresh.setOnRefreshListener {
            (binding.rvFeed.adapter as FeedAdapter).refresh()
        }
        startPagingLoad()
    }

    private fun setButton(){
        binding.ivbtnAddFeed.setOnClickListener {
            val permissionCheck = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(activity, ImagePickerActivity::class.java)
                feedWriteResult.launch(intent)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun startPagingLoad(){
        pagingJob?.cancel()
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

    private fun setRecyclerView() {
        binding.rvFeed.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFeed.adapter = FeedAdapter()
    }


}