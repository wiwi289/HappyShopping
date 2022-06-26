package com.swu._main.widget

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.swu._main.databinding.VerifyCodePanelLayoutBinding
import com.swu.base.BaseView
import com.swu.base.viewBinding
import java.util.regex.Pattern

class LoginByVerifyCodePanel @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

    override val binding: VerifyCodePanelLayoutBinding = viewBinding(VerifyCodePanelLayoutBinding::inflate, true)

    private var shouldAutoSplit = true

    init {
        binding.inputNum.setExtraTextWatcher(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                shouldAutoSplit = count == 1
            }

            override fun afterTextChanged(s: Editable?) {
                if (shouldAutoSplit) {
                    s.toString().length.also { len ->
                        if (len == 3 || len == 8) {
                            s?.append(" ")
                        }
                    }
                }
            }
        })
    }

    override fun adjust() { }

    override fun getMBinding() = binding

    fun getPhoneNum() = binding.inputNum.getPhoneNumber()

    fun isValidPhoneNum() = binding.inputNum.isValidPhoneNum()

    fun getVerifyCode() = binding.verifyCode.text.toString()
}