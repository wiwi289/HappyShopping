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
import com.swu.common.extensions.inflate
import com.swu.common.model.GoodsCategoryModel
import com.swu.feed.R
import com.swu.feed.databinding.RightMenuItemLayoutBinding

class RightMenuAdapter: RecyclerView.Adapter<RightMenuAdapter.RightMenuVH>() {

    private var data: List<GoodsCategoryModel>? = null

    class RightMenuVH(binding: RightMenuItemLayoutBinding): BaseVH<RightMenuItemLayoutBinding, GoodsCategoryModel>(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(data: GoodsCategoryModel) {
            Glide.with(binding.root.context)
                .load(data.imgUrl)
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
                itemName.isSelected = true
                tvSaleCount.text = "月销${data.sales}"
                itemGoodPrice.text = "￥${data.price}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RightMenuVH =
            RightMenuVH(RightMenuItemLayoutBinding::class.java.inflate(parent))

    override fun onBindViewHolder(holder: RightMenuVH, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun setData(data: List<GoodsCategoryModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getGroupIndex(leftMenuTitle: String, pos: Int): Int{
        if (data == null) return 0
        for (i in data!!.indices) {
            if (data!![i].getGroupName() == leftMenuTitle) return i
        }
        return 0
    }

    fun getGroupName(pos: Int) = data?.get(pos)?.getGroupName() ?: "衣服"

}