package com.swu.order.widget

import android.content.Context
import android.util.AttributeSet
import com.swu.base.BaseView
import com.swu.base.viewBinding
import com.swu.order.databinding.GoodsDetailParamLayoutBinding

class GoodsDetailParamLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style){

    override val binding: GoodsDetailParamLayoutBinding = viewBinding(GoodsDetailParamLayoutBinding::inflate, true)

    override fun adjust() { }

    override fun getMBinding() = binding

    fun setAddress(address: String) {
        if (address.isNotEmpty()) binding.tvAddress.text = address
    }
}