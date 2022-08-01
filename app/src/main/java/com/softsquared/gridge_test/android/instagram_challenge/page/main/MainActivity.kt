package com.softsquared.gridge_test.android.instagram_challenge.page.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityMainBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: BaseViewModel? = null

    private val homeFragment = HomeFragment()
    private var currentFragment : Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().add(binding.layoutMain.id, homeFragment).commit()

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> {
                    if (currentFragment != homeFragment) {
                        supportFragmentManager.beginTransaction().hide(currentFragment).show(homeFragment).commit()
                    }
                }
                R.id.bottom_search -> {

                }
                R.id.bottom_video -> {

                }
                R.id.bottom_shop -> {

                }
                R.id.bottom_mypage -> {

                }
                else -> {

                }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.bottom_home
    }


}