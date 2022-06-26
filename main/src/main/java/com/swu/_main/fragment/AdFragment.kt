package com.swu._main.fragment

import com.swu._main.MainViewModel
import com.swu._main.R
import com.swu.base.BaseFragment
import com.swu.base.viewBinding

import com.swu._main.databinding.FragmentAdBinding

class AdFragment(private val viewModel: MainViewModel) : BaseFragment() {

    override val binding: FragmentAdBinding by viewBinding(FragmentAdBinding::inflate)

    override fun init() {
        binding.secondsBtn.startCountDown()
    }

    override fun initListener() {
        binding.secondsBtn.setCountFinishListener {
            viewModel.replaceFragment(LoginFragment(viewModel), R.anim.page_from_right_to_left_in)
        }
    }

}