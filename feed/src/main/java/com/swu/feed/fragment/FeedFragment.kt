package com.swu.feed.fragment

import com.swu._main.view_model.FeedViewModel
import com.swu.base.BaseFragment
import com.swu.base.viewBinding
import com.swu.feed.databinding.FragmentFeedBinding

class FeedFragment(private val feedViewModel: FeedViewModel) : BaseFragment() {

    override val binding: FragmentFeedBinding by viewBinding(FragmentFeedBinding::inflate)

    override fun init() {

    }

    override fun initListener() {

    }


}