package com.swu._main.fragment

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import com.liys.dialoglib.LDialog
import com.swu._main.MainViewModel
import com.swu._main.R
import com.swu._main.bean.BombUserResp
import com.swu._main.constants.LoginEventConstants
import com.swu._main.databinding.FragmentLoginBinding
import com.swu.base.BaseFragment
import com.swu.base.show
import com.swu.base.viewBinding
import com.swu.common.defined_event_bus.DefinedEventCallBack
import com.swu.common.defined_event_bus.DefinedEventController
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

class LoginFragment(private val viewModel: MainViewModel) : BaseFragment(), DefinedEventCallBack {

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::inflate)

    override fun init() {
        DefinedEventController.registerCallBack(LoginEventConstants.QueryUser, callBack = this)
    }

    override fun initListener() {
        with(binding.loginWayPanel.getMBinding()) {
            textForgetPwd.setOnClickListener { showForgetPwdDialog() }
            textLoginByPhone.setOnClickListener { startFragment(LoginByVerifyCodeFragment(viewModel)) }

        }

        with(binding.loginPanel.getMBinding()) {
            btnLogin.setOnClickListener { loginBtnDown() }
        }

        binding.loginRegister.setOnClickListener { startFragment(RegisterFragment(viewModel)) }
    }

    @SuppressLint("CheckResult")
    private fun loginBtnDown() {
            Observable.create(ObservableOnSubscribe<Boolean> {
                val res = if (binding.loginPanel.checkInputValid()) {
                    true
                } else {
                    "手机号和密码输入不能为空!!!".show()
                    false
                }
                it.onNext(res)
            }).subscribe { res->
                if (!res) return@subscribe
                viewModel.queryUser(binding.loginPanel.getUserId())
            }
    }

    private fun showForgetPwdDialog() {
        LDialog.newInstance(viewModel.getMContext(), R.layout.forget_pwd_dialog_layout)
            .setGravity(Gravity.BOTTOM)
            .setOnClickListener({ view: View, lDialog: LDialog ->
                when(view.id) {
                    R.id.resetPwd -> startFragment(ResetPwdFragment(viewModel))
                }
                lDialog.dismiss()
            }, R.id.loginByFace, R.id.resetPwd, R.id.cancelBtn)
            .show()
    }

    private fun startFragment(fragment: Fragment) {
        viewModel.startFragment(fragment, R.anim.page_from_right_to_left_in)
    }

    override fun onEventChange(event: String, res: Any?) {
        when(event) {
            LoginEventConstants.QueryUser -> (res as? BombUserResp)?.let { checkLogin(it)}

        }
    }

    private fun checkLogin(bombUserResp: BombUserResp) {
        if (binding.loginPanel.checkPwd(bombUserResp.getUser().pwd)){
            // 跳转至主Activity
            "登录成功!".show()
            DefinedEventController.dispatchEvent(LoginEventConstants.JumpLaunchToNavigation)
        } else "密码错误，请重新输入!!!".show()
    }
}