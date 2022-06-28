package com.swu.order.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.swu.base.BaseView
import com.swu.base.viewBinding
import com.swu.order.databinding.GoodsDetailHeaderLayoutBinding

class GoodsDetailHeaderLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style){

    override val binding: GoodsDetailHeaderLayoutBinding = viewBinding(GoodsDetailHeaderLayoutBinding::inflate, true)

    override fun adjust() { }

    override fun getMBinding() = binding

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    @SuppressLint("SetTextI18n")
    fun setPromotionPrice(price: String) {
        binding.tvPrice.text = "￥${price}"
    }

    fun setOriginalPrice(price: String) {
        with(binding.tvOriginalP) {
            text = price
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    fun setSalesCount(sales: Int) {
        binding.tvSales.text = sales.toString()
    }

}