package com.softsquared.gridge_test.android.instagram_challenge.base_component

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> (@LayoutRes val layoutRes : Int) : AppCompatActivity() {
    protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preLoad()

        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    open fun setButton(){}

    open fun setRecyclerView(){}

    open fun preLoad() {}
}