package com.swu.feed.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.swu.base.recyclerview_v2.BaseVH
import com.swu.common.bean.FeedGoodsBean
import com.swu.common.bean.FeedHotGoodsBean
import com.swu.common.extensions.inflate
import com.swu.feed.R
import com.swu.feed.databinding.FeedItemLayoutBinding

class FeedAdapter: RecyclerView.Adapter<FeedAdapter.FeedVH>() {

    private var feedDataList: List<FeedHotGoodsBean>? = null

    class FeedVH(binding: FeedItemLayoutBinding) : BaseVH<FeedItemLayoutBinding, FeedHotGoodsBean>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: FeedHotGoodsBean) {
            Glide.with(binding.root.context)
                .load(data.picUrl)
                .placeholder(R.drawable.photo_place_holder)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.also { binding.imgShimmer.stopShimmerAnimation() }
                    }

                })
                .into(binding.itemImg)

            with(binding) {
                imgShimmer.apply {
                    setShimmerColor(0x55FFFFFF)
                    setShimmerAngle(0)
                    startShimmerAnimation()
                }
                itemName.text = data.title
                itemGoodPrice.text = "￥${data.promotionPrice}"
                tvSaleCount.text = "${data.sales}人付款"
                shopName.text = data.shopName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVH =
        FeedVH(FeedItemLayoutBinding::class.java.inflate(parent))

    override fun onBindViewHolder(holder: FeedVH, position: Int) {
        feedDataList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = feedDataList?.size ?: 0

    fun setData(data: List<FeedHotGoodsBean>) {
        this.feedDataList = data
        notifyDataSetChanged()
    }
}