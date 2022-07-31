package com.softsquared.gridge_test.android.instagram_challenge.base_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding> (private val bind : (View) -> B, @LayoutRes private val layoutResId : Int) : Fragment() {
    private var _binding : B ?= null
    protected val binding get() = _binding!!
    protected abstract val viewModel : BaseViewModel?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel?.isShowLoadingDialog?.collect { showDialog ->
                        if (showDialog) {
                            (activity as BaseActivity<*>).showLoadingDialog()
                        } else {
                            (activity as BaseActivity<*>).dismissLoadingDialog()
                        }
                    }
                }
                launch {
                    viewModel?.networkErrorMessage?.collect { errorMessage ->
                        (activity as BaseActivity<*>).showSimpleToastMessage(errorMessage)
                    }
                }
            } // repeatOnLifecycle
        } // lifecycleScope.launch
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}