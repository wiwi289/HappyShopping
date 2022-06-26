package com.swu.happyshopping

import android.util.Log
import com.swu.base.BaseActivity
import com.swu.base.viewBinding
import com.swu.happyshopping.databinding.ActivitySplashBinding
class SplashActivity : BaseActivity() {

    override val binding: ActivitySplashBinding by viewBinding(ActivitySplashBinding::inflate)

    override fun initData() {
        immerseStatusBar()
        Log.e("cx",binding.launchImage.id.toString())
    }

    override fun initListener() { }

}