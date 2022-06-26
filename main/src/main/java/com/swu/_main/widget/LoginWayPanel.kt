package com.swu._main.widget

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.swu._main.databinding.MoreLoginWayLayoutBinding
import com.swu.base.BaseView
import com.swu.base.viewBinding

// 选择三方登录或者短信验证码登录的View
class LoginWayPanel @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

    override val binding: MoreLoginWayLayoutBinding = viewBinding(MoreLoginWayLayoutBinding::inflate, true)

    override fun adjust() { }

    override fun getMBinding() = binding
}