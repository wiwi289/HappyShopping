package com.swu.feed.fragment

import com.swu.feed.view_model.FeedViewModel
import com.swu.base.BaseFragment
import com.swu.base.viewBinding
import com.swu.feed.databinding.FragmentMineBinding


class MineFragment(private val feedViewModel: FeedViewModel) : BaseFragment() {

    override val binding: FragmentMineBinding by viewBinding(FragmentMineBinding::inflate)

    override fun init() {

    }

    override fun initListener() {
        binding.bactText.setOnClickListener {
            feedViewModel.switchFragment(this, FeedFragment(feedViewModel))
        }
    }

}