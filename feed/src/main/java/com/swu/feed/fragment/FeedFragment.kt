package com.swu.feed.fragment

import android.os.Bundle
import android.os.HandlerThread
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.swu.feed.view_model.FeedViewModel
import com.swu.base.BaseFragment
import com.swu.base.show
import com.swu.base.viewBinding
import com.swu.common.util.HandlerManager
import com.swu.feed.R
import com.swu.feed.adapter.FeedAdapter
import com.swu.feed.databinding.FragmentFeedBinding
import com.swu.feed.debug.TestActivity
import com.swu.feed.widget.CategoryItem
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class FeedFragment(private val feedViewModel: FeedViewModel) : BaseFragment() {

    override val binding: FragmentFeedBinding by viewBinding(FragmentFeedBinding::inflate)

    private val bannerPics = listOf(
        R.drawable.binner_pic1,
        R.drawable.binner_pic2,
        R.drawable.binner_pic3,
        R.drawable.binner_pic4,
        R.drawable.binner_pic5
    )

    private val feedPageAdapter = FeedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun init() {
        (feedViewModel.getMContext() as? TestActivity)?.setSupportActionBar(binding.toolbar)
        (feedViewModel.getMContext() as? TestActivity)?.supportActionBar?.title = ""

        binding.banner
            .setAdapter(object : BannerImageAdapter<Int>(bannerPics) {
                override fun onBindView(holder: BannerImageHolder, data: Int, position: Int, size: Int) {
                    holder.imageView.setImageResource(data)
                }
            })
            .addBannerLifecycleObserver(this)
            .indicator = CircleIndicator(feedViewModel.getMContext())

        binding.feedRv.apply {
            adapter = feedPageAdapter
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        binding.loadingView.startMoving()
        HandlerManager.doInSubThread({
            feedViewModel.fetchFeedPageHotData()
        }, 1500)

    }

    override fun initListener() {
        CategoryItem.setOnItemClickCallBack {
            feedViewModel.leftMenuSelectedId = it.index
            feedViewModel.startFragment(GoodsCategoryListFragment(feedViewModel))
        }

        feedViewModel.hotFeedData.observe(this) {
            with(binding.loadingView) {
                stopMoving()
                visibility = View.GONE
            }
            binding.feedRv.visibility = View.VISIBLE
            feedPageAdapter.setData(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.feed_tool_bar_search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.feedSearchBtn -> {
                "搜索按钮被点击了".show()
                feedViewModel.switchFragment(this, MineFragment(feedViewModel))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}