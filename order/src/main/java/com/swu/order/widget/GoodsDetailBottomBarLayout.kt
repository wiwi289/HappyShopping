package com.swu.order.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.swu.base.BaseApplication.getContext
import com.swu.base.BaseView
import com.swu.base.UIUtils
import com.swu.base.viewBinding
import com.swu.common.util.ScreenSizeUtil
import com.swu.order.databinding.GoodsDetailBottomBarLayoutBinding
import java.lang.ref.WeakReference

class GoodsDetailBottomBarLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style){

    override val binding: GoodsDetailBottomBarLayoutBinding = viewBinding(GoodsDetailBottomBarLayoutBinding::inflate, true)

    private var mActivityRef: WeakReference<Activity>? = null

    override fun adjust() { }

    override fun getMBinding() = binding

    private var bottomBarHeight = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bottomBarHeight = measuredHeight
    }

    fun attach(activity: Activity) {
        if (parent == null) {
            mActivityRef = WeakReference(activity)
            x = 0f
            // 疑惑高度什么时候获取
            y = ((ScreenSizeUtil.getScreenHeight(context) - UIUtils.dp2px(mActivityRef?.get()!!, 40)).toFloat())
            activity.window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(this,binding.root.layoutParams)
        }
    }

    fun detach() {
        mActivityRef?.get()?.window?.decorView?.findViewById<ViewGroup>(android.R.id.content)
            ?.removeView(this)
        mActivityRef = null
    }

    companion object {
        val BOTTOM_CART_HEIGHT:Int = (56 / 881.0f * ScreenSizeUtil.getScreenHeight(
            getContext()
        )).toInt()
    }
}