package com.swu.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding

abstract class BaseView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : FrameLayout(context, attr, style){

    protected abstract val binding: ViewBinding

    abstract fun adjust()

    abstract fun getMBinding(): ViewBinding
}