package com.swu._main.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.swu.base.BaseView
import com.swu.base.viewBinding
import com.swu._main.databinding.LoginPanelLayoutBinding
class LoginPanel@JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

     override val binding: LoginPanelLayoutBinding = viewBinding(LoginPanelLayoutBinding::inflate, true)


    init {
        binding.btnLogin.setOnClickListener {

        }
    }

    override fun adjust() { }

    override fun getMBinding() = binding

    fun checkInputValid() =
        !(binding.inputNum.text.isNullOrEmpty() || binding.inputPwd.text.isNullOrEmpty())

    fun getUserId() = binding.inputNum.text.toString()

    fun getPwd() = binding.inputPwd.text.toString()

    fun checkPwd(pwd: String): Boolean = binding.inputPwd.text.toString() == pwd
}