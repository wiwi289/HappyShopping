package com.swu._main.fragment

import android.annotation.SuppressLint
import com.swu._main.MainViewModel
import com.swu._main.R
import com.swu.common.constants.BombMethodConstants
import com.swu.common.constants.LoginEventConstants
import com.swu._main.databinding.FragmentResetPwdBinding
import com.swu.base.BaseFragment
import com.swu.base.show
import com.swu.base.viewBinding
import com.swu.common.defined_event_bus.DefinedEventCallBack
import com.swu.common.defined_event_bus.DefinedEventController
import com.swu.common.util.BombMsg
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

class ResetPwdFragment(private val viewModel: MainViewModel) : BaseFragment(),
    DefinedEventCallBack {

    override val binding: FragmentResetPwdBinding by viewBinding(FragmentResetPwdBinding::inflate)

    private val bombMsg: BombMsg by lazy { BombMsg() }

    override fun init() {
        DefinedEventController.registerCallBack(LoginEventConstants.ResetUserPwd, callBack = this)
    }

    override fun initListener() {
        binding.backBtn.setOnClickListener { closeNowFrag() }

        binding.quickLoginBtn.setOnClickListener { closeNowFrag() }

        with(binding.resetPwdPanel.getMBinding()) {
            thirdWayBtn.setOnClickListener { closeNowFrag() }
            verifyCode.setVerifyCallBack { sendMsgCode() }
            btnSure.setOnClickListener { checkResetBtn() }
        }
    }

    private fun closeNowFrag() {
        viewModel.closeFragment(this, R.anim.page_from_top_to_bottom_out)
    }

    @SuppressLint("CheckResult")
    private fun checkResetBtn() {
        Observable.create(ObservableOnSubscribe<Boolean> {
            val res = if (!binding.resetPwdPanel.isValidPhoneNum()) {
                "输入的手机号不合法，请重新输入!!!".show()
                false
            } else true
            it.onNext(res)
        }).map { upRes ->
            val res = if (binding.resetPwdPanel.checkDoublePwdEquals()) {
                true
            } else {
                "两次输入的密码不一致，请重新输入!!!".show()
                false
            }
            upRes && res
        }.map { upRes ->
            val res = if (binding.resetPwdPanel.getVerifyCode().length != 6) {
                "验证码长度不足，请继续输入!!!".show()
                false
            } else true
            upRes && res
        }.subscribe { res ->
            if (!res) return@subscribe
//            bombMsg.verifyCode(binding.registerPanel.getPhoneNum(), binding.registerPanel.getVerifyCode()) { success ->
//                if (success) {
//                     viewModel.resetUserPwd(binding.resetPwdPanel.getPhoneNum(),binding.resetPwdPanel.getFirstPwd())
//                } else {
//                    "注册失败！".show()
//                }
//            }
            viewModel.resetUserPwd(binding.resetPwdPanel.getPhoneNum(),binding.resetPwdPanel.getFirstPwd())
            closeNowFrag()
        }
    }

    @SuppressLint("CheckResult")
    private fun sendMsgCode(): Boolean {
        var finalRes = false
        Observable.create(ObservableOnSubscribe<Boolean> {
            val res = if (!binding.resetPwdPanel.isValidPhoneNum()) {
                "输入的手机号不合法，请重新输入!!!".show()
                false
            } else true
            it.onNext(res)
        }).map { upRes ->
            val res = if (binding.resetPwdPanel.checkDoublePwdEquals()) {
                true
            } else {
                "两次输入密码一致才能发送验证码".show()
                false
            }
            upRes && res
        }.subscribe { res ->
            if (!res) return@subscribe
//            bombMsg.sendMsgCode(binding.registerPanel.getPhoneNum(), mainViewModel.getMContext())
            "验证码发送成功".show()
            finalRes = true
        }
        return finalRes
    }

    override fun onEventChange(event: String, res: Any?) {
        when(event) {
            BombMethodConstants.ResetUserPwd -> closeNowFrag()
        }
    }
}