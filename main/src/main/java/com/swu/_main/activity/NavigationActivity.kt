package com.swu._main.activity

import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.swu._main.R
import com.swu._main.databinding.ActivityNavigationBinding
import com.swu.base.BaseActivity
import com.swu.base.viewBinding

class NavigationActivity : BaseActivity() {

    override val binding: ActivityNavigationBinding by viewBinding(ActivityNavigationBinding::inflate)

    override fun initData() {
        initBar()
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
}