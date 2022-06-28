package com.swu.mine.debug

import androidx.lifecycle.ViewModelProvider
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.swu.base.BaseActivity
import com.swu.base.FragmentController
import com.swu.base.viewBinding
import com.swu.base.view_model.BaseViewModelFactory
import com.swu.mine.R

import com.swu.mine.databinding.ActivityTestBinding

class TestActivity : BaseActivity() {

    override val binding: ActivityTestBinding by viewBinding(ActivityTestBinding::inflate)

    private val fragmentController: FragmentController by lazy {
        FragmentController(this, binding.container.id)
    }

//    private val feedViewModel: FeedViewModel by lazy {
//        ViewModelProvider(this, BaseViewModelFactory(this, fragmentController)).get(FeedViewModel::class.java)
//    }

    override fun initData() {
        initBar()
//        feedViewModel.startFragment(FeedFragment(feedViewModel))
    }


    override fun initListener() {
        binding.bottomNavigationBar.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabReselected(position: Int) {

            }
        })

    }

    private fun initBar() {
        binding.bottomNavigationBar.clearAll()
        binding.bottomNavigationBar
            .addItem(BottomNavigationItem(R.drawable.ic_home_24, R.string.home_title))
            .addItem(BottomNavigationItem(R.drawable.ic_select_message_24, R.string.message_title))
            .addItem(BottomNavigationItem(R.drawable.ic_select_shopping_cart_24, R.string.shop_cart_title))
            .addItem(BottomNavigationItem(R.drawable.ic_select_mine_24, R.string.mine_title))
            .setFirstSelectedPosition(0)
            .initialise()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}