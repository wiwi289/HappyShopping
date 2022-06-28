package com.swu.feed.fragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swu.base.BaseFragment
import com.swu.base.UIUtils
import com.swu.base.updateLayoutParams
import com.swu.base.viewBinding
import com.swu.common.util.HandlerManager
import com.swu.feed.R
import com.swu.feed.adapter.LeftMenuAdapter
import com.swu.feed.adapter.RightMenuAdapter
import com.swu.feed.databinding.FragmentGoodsCategoryListBinding
import com.swu.feed.databinding.ItemShopDetailsMenuRightGroupBinding
import com.swu.feed.helper.FloatDecoration
import com.swu.feed.view_model.FeedViewModel
import com.swu.feed.widget.CenterLayoutManager


class GoodsCategoryListFragment(private val feedViewModel: FeedViewModel) : BaseFragment() {

    override val binding: FragmentGoodsCategoryListBinding by viewBinding(FragmentGoodsCategoryListBinding::inflate)


    private val leftMenuAdapter = LeftMenuAdapter()
    private val leftRvLayoutManager = CenterLayoutManager(feedViewModel.getMContext())
    private val rightMenuAdapter = RightMenuAdapter()
    private var mRvState = RecyclerView.State()

    override fun init() {
        binding.rvLeftMenu.apply {
            adapter = leftMenuAdapter
            layoutManager = leftRvLayoutManager
        }
        binding.rvRightMenu.apply {
            adapter = rightMenuAdapter
            layoutManager = LinearLayoutManager(feedViewModel.getMContext())
        }

        binding.loadingView.startMoving()
        HandlerManager.doInUIThread({
            feedViewModel.fetchAllCategoryData()
        }, 1500)
    }

    override fun initListener() {
        feedViewModel.categoryLiveData.observe(this) { list ->
            with(binding.loadingView) {
                visibility = View.GONE
                stopMoving()
            }
            binding.rvContainer.visibility = View.VISIBLE
            rightMenuAdapter.setData(list)
            switchLeftMenuPos(feedViewModel.leftMenuSelectedId)
        }
        binding.rvRightMenu.addItemDecoration(
            FloatDecoration(
                ItemShopDetailsMenuRightGroupBinding.inflate(
                    UIUtils.getLayoutInflater(feedViewModel.getMContext()),
                    binding.rvRightMenu,
                    false
                ),
                object : FloatDecoration.DecorationCallback<ItemShopDetailsMenuRightGroupBinding> {
                    override fun getDecorationFlag(position: Int): String {
                        return rightMenuAdapter.getGroupName(position)
                    }

                    override fun onBindView(
                        binding: ItemShopDetailsMenuRightGroupBinding,
                        position: Int
                    ) {
                        binding.tvGroupName.text = rightMenuAdapter.getGroupName(position)
                    }

                }
            )
        )
        binding.scrollView.post {
            binding.rvLeftMenu.updateLayoutParams<ViewGroup.LayoutParams> {
                height = binding.scrollView.height
            }
            binding.rvRightMenu.updateLayoutParams<ViewGroup.LayoutParams> {
                height = binding.scrollView.height
            }
        }
        leftMenuAdapter.setOnItemClickCallBack{ pos ->
            switchLeftMenuPos(pos)
        }
        binding.rvRightMenu.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (binding.rvRightMenu.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (leftMenuAdapter.getTitle(leftMenuAdapter.getCurSelectedIndex()) != rightMenuAdapter.getGroupName(position)) {
                    with(leftMenuAdapter) {
                        reFreshSelectStatus(getTitleIndex(rightMenuAdapter.getGroupName(position)))
                    }
                    leftRvLayoutManager.smoothScrollToPosition(
                        binding.rvLeftMenu,
                        mRvState,
                        leftMenuAdapter.getCurSelectedIndex()
                    )

                }
            }
        })

        binding.backBtn.setOnClickListener {
            feedViewModel.closeFragment(this, R.anim.page_from_top_to_bottom_out)
        }

    }

    private fun switchLeftMenuPos(pos: Int) {
        rightMenuAdapter.getGroupIndex(leftMenuAdapter.getTitle(pos), pos).let { index ->
            (binding.rvRightMenu.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                index, 0
            )
        }
    }

}