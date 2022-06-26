package com.swu._main.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.swu._main.R
import com.swu._main.databinding.RegisterPanelLayoutBinding
import com.swu.base.BaseView
import com.swu.base.viewBinding

class PwdRegisterPanel @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

    override val binding: RegisterPanelLayoutBinding = viewBinding(RegisterPanelLayoutBinding::inflate, true)

    private var shouldAutoSplit = true

    init {
        val typedArr = context.obtainStyledAttributes(attr, R.styleable.PwdRegisterPanel)
        binding.btnSure.text = typedArr.getText(R.styleable.PwdRegisterPanel_sure_btn_text)
        typedArr.recycle()

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

    fun getFirstPwd() = binding.firstPwd.text.toString()

    fun getSecondPwd() = binding.secondPwd.text.toString()

    fun checkDoublePwdEquals() = getFirstPwd() == getSecondPwd()

}