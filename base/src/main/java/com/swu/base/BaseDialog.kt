package com.swu.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.swu.lib_base.R

/**
 * Created by chenxiong
 * date 11/26/21
 */
abstract class BaseDialog(context: Context, style: Int = R.style.BaseDialog) :
    Dialog(context, style) {

    protected abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        initData()
        initListener()
    }
    private fun init() {

    }
    abstract fun initData()

    abstract fun initListener()

    protected fun getRoundRectDrawable(radius: Int, color: Int): GradientDrawable? {
        //左上、右上、右下、左下的圆角半径
        val radiuss = floatArrayOf(
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat()
        )
        val drawable = GradientDrawable()
        drawable.cornerRadii = radiuss
        drawable.setColor(if (color != 0) color else Color.TRANSPARENT)
        return drawable
    }
}