package com.swu._main

import androidx.lifecycle.ViewModelProvider
import com.swu._main.databinding.ActivityLaunchBinding
import com.swu._main.fragment.*
//import com.swu._main.fragment.AdFragment
import com.swu.base.BaseActivity
import com.swu.base.FragmentController
import com.swu.base.viewBinding
import com.swu.base.view_model.BaseViewModelFactory

class LaunchActivity : BaseActivity() {

    override val binding: ActivityLaunchBinding by viewBinding(ActivityLaunchBinding::inflate)

    private val fragmentController: FragmentController by lazy {
        FragmentController(this, binding.container.id)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, BaseViewModelFactory(this, fragmentController)).get(MainViewModel::class.java)
    }

    override fun initData() {

    }

    override fun initListener() {
//        mainViewModel.startFragment(AdFragment(mainViewModel))
        mainViewModel.startFragment(LoginFragment(mainViewModel))
//        mainViewModel.startFragment(RegisterFragment(mainViewModel))
//        mainViewModel.startFragment(ResetPwdFragment(mainViewModel))
//        mainViewModel.startFragment(LoginByVerifyCodeFragment(mainViewModel))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}