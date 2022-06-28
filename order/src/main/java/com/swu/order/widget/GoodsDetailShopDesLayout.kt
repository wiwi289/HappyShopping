package com.swu.order.widget

import android.content.Context
import android.util.AttributeSet
import com.swu.base.BaseView
import com.swu.base.viewBinding
import com.swu.order.databinding.GoodsDetailShopDesLayoutBinding

class GoodsDetailShopDesLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

    override val binding: GoodsDetailShopDesLayoutBinding = viewBinding(GoodsDetailShopDesLayoutBinding::inflate, true)

    override fun adjust() { }

    override fun getMBinding() = binding

    fun setGoodsDes(goodsScore: String) {
        if (goodsScore.isNullOrEmpty()) {
            binding.tvGoodsScore.text = "4.6"
            binding.tvGoodsLevel.text = "平"
        } else {
            binding.tvGoodsScore.text = goodsScore
            binding.tvGoodsLevel.text = "高"
        }
    }

    fun setSellerDes(sellerScore: String) {
        if (sellerScore.isNullOrEmpty()) {
            binding.tvSellerScore.text = "4.6"
            binding.tvSellerLevel.text = "平"
        } else {
            binding.tvSellerScore.text = sellerScore
            binding.tvSellerLevel.text = "高"
        }
    }

    fun setDispatchDes(dispatchScore: String) {
        if (dispatchScore.isNullOrEmpty()) {
            binding.tvDispatchScore.text = "4.6"
            binding.tvDispatchLevel.text = "平"
        } else {
            binding.tvDispatchScore.text = dispatchScore
            binding.tvDispatchLevel.text = "高"
        }
    }

    fun setShopName(name: String) {
        binding.shopName.text = name
    }
}