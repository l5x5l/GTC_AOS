package com.softsquared.gridge_test.android.instagram_challenge.page.mypage

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseFragment
import com.softsquared.gridge_test.android.instagram_challenge.databinding.FragmentMypageBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.mypage.feedlist.MyPageFeedListFragment
import com.softsquared.gridge_test.android.instagram_challenge.page.mypage.taglist.MyPageTagListFragment
import com.softsquared.gridge_test.android.instagram_challenge.utils.moveToLoginPage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyPageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage) {
    override val viewModel: MyPageViewModel by lazy { ViewModelProvider(this)[MyPageViewModel::class.java] }
    private val feedFragment = MyPageFeedListFragment()
    private val tagFragment = MyPageTagListFragment()
    private var currentFragment : BaseFragment<*> = feedFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadMyPageDataResult.collect { resultCode ->
                    when (resultCode) {
                        1000 -> { /* success, 데이터 UI 반영은 stateFlow & bindingAdapter 사용 */}
                        3001 -> {
                            moveToLoginPage(activity as BaseActivity<*>)
                        }
                        else -> {
                            (activity as BaseActivity<*>).showSimpleToastMessage(getString(R.string.message_network_error))
                        }
                    }
                }
            }
        }

        setButton()
        setChildFragment()

        viewModel.tryGetMyPageData()
    }

    private fun setChildFragment(){
        childFragmentManager.beginTransaction().add(binding.layoutFragment.id, tagFragment).hide(tagFragment)
            .add(binding.layoutFragment.id, feedFragment).commit()
    }

    private fun setButton(){
        binding.ivbtnMenu.setOnClickListener {
            moveToLoginPage(activity as BaseActivity<*>)
        }

        binding.layoutbtnTaged.setOnClickListener {
            if (currentFragment !is MyPageTagListFragment) {
                binding.ivTaged.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black))
                binding.lineTaged.visibility = View.VISIBLE
                binding.ivMyFeedList.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray600))
                binding.lineMyFeedList.visibility = View.GONE

                childFragmentManager.beginTransaction().hide(currentFragment).show(tagFragment).commit()
                currentFragment = tagFragment
            }
        }

        binding.layoutbtnMyFeedList.setOnClickListener {
            if (currentFragment !is MyPageFeedListFragment){
                binding.ivTaged.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray600))
                binding.lineTaged.visibility = View.GONE
                binding.ivMyFeedList.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black))
                binding.lineMyFeedList.visibility = View.VISIBLE

                childFragmentManager.beginTransaction().hide(currentFragment).show(feedFragment).commit()
                currentFragment = feedFragment
            }
        }
    }

    fun refreshFeedList(){
        feedFragment.refreshFeedList()
    }
}