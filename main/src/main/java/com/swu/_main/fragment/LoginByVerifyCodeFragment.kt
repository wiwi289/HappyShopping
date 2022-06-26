package com.swu._main.fragment

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.swu._main.MainViewModel
import com.swu._main.R
import com.swu._main.databinding.FragmentLoginByVerifyCodeBinding
import com.swu.base.BaseFragment
import com.swu.base.show
import com.swu.base.viewBinding
import com.swu.common.util.BombMsg
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.coroutines.launch

class LoginByVerifyCodeFragment(private val viewModel: MainViewModel) : BaseFragment() {

    override val binding: FragmentLoginByVerifyCodeBinding
            by viewBinding(FragmentLoginByVerifyCodeBinding::inflate)

    private val bombMsg: BombMsg by lazy { BombMsg() }

    override fun init() { }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initListener() {
        binding.backBtn.setOnClickListener { closeNowFrag() }

        with(binding.verifyCodePanel.getMBinding()) {
            thirdWayBtn.setOnClickListener { closeNowFrag() }

            btnSure.setOnClickListener { checkLogin() }

            verifyCode.setVerifyCallBack {
                val res = if (binding.verifyCodePanel.isValidPhoneNum()) {
                    bombMsg.sendMsgCode(binding.verifyCodePanel.getPhoneNum(), viewModel.getMContext())
                    true
                } else {
                    "输入的手机号不合法，请重新输入!!!".show()
                    false
                }
                res
            }
        }
    }

    private fun closeNowFrag() {
        viewModel.closeFragment(this, R.anim.page_from_top_to_bottom_out)
    }

    @SuppressLint("CheckResult")
    private fun checkLogin() {
        Observable.create(ObservableOnSubscribe<Boolean> {
            val res = if (!binding.verifyCodePanel.isValidPhoneNum()) {
                "输入的手机号不合法，请重新输入!!!".show()
                false
            } else true
            it.onNext(res)
        }).map { upRes ->
            val res = if (binding.verifyCodePanel.getVerifyCode().length != 6) {
                "验证码长度不足，请继续输入!!!".show()
                false
            } else true
            upRes && res
        }.subscribe { res ->
            if (!res) return@subscribe
//            bombMsg.verifyCode(binding.verifyCodePanel.getPhoneNum(), binding.verifyCodePanel.getVerifyCode()) { success ->
//                if (success) {
//                    "注册成功！".show()
//                } else {
//                    "注册失败！".show()
//                }
//            }
            viewModel.viewModelScope.launch {
                val bombUserResp = viewModel.getUser(binding.verifyCodePanel.getPhoneNum())
                if (bombUserResp == null || bombUserResp.results.isEmpty()) {
                    "用户不存在，请检查账号输入是否有误!!!".show()
                } else {
                    "登录成功".show()
                    jumpToMainPage()
                }
            }
        }
    }

    private fun jumpToMainPage() {

    }
}