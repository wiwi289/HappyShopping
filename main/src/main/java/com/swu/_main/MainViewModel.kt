package com.swu._main

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.AsyncCustomEndpoints
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.CloudCodeListener
import com.swu._main.bean.BombUserResp
import com.swu.common.constants.BombMethodConstants
import com.swu.common.constants.LoginEventConstants
import com.swu.base.FragmentController
import com.swu.base.show
import com.swu.base.view_model.BaseViewModel
import com.swu.common.defined_event_bus.DefinedEventController
import com.swu.common.network.ApiParam
import com.swu.common.network.BombRequest
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(context: Context, fragmentController: FragmentController) : BaseViewModel(context, fragmentController) {

     fun queryUser(userId: String) {
        viewModelScope.launch {
            val bombUserResp = getUser(userId)
            if (bombUserResp == null || bombUserResp.results.isEmpty()) {
                "用户不存在，请检查账号输入是否有误!!!".show()
            } else {
                DefinedEventController.dispatchEvent(LoginEventConstants.QueryUser, bombUserResp)
            }
        }
    }

    suspend fun getUser(userId: String): BombUserResp? = BombRequest.Get<BombUserResp>()
        .setMethodName(BombMethodConstants.QueryUserMethod)
        .setApiParam(ApiParam("account", userId))
        .await()

    fun resetUserPwd(userId: String, newPwd: String) {
        viewModelScope.launch {

            val bombUserResp = getUser(userId)

            if (bombUserResp == null || bombUserResp.results.isEmpty()) {
                "用户不存在，请检查账号输入是否有误!!!".show()
                return@launch
            }
            BombRequest.Get<Any>()
                .setMethodName(BombMethodConstants.ResetUserPwd)
                .setApiParam(
                    ApiParam("objId", bombUserResp.getUser().objectId)
                    .append("newPwd", newPwd)
                )
                .emit { res ->
                    if (res) {
                        "重置密码成功".show()
                        DefinedEventController.dispatchEvent(LoginEventConstants.ResetUserPwd)
                    } else {
                        "请检查手机号是否输入正确".show()
                    }
                }
        }
    }

    fun registerUser(account: String, pwd: String) {
        viewModelScope.launch {
            BombRequest.Get<Any>()
                .setMethodName(BombMethodConstants.AddUser)
                .setApiParam(
                    ApiParam("account", account)
                        .append("pwd", pwd)
                )
                .emit { res ->
                    if (res) {
                        "注册成功".show()
                        DefinedEventController.dispatchEvent(LoginEventConstants.RegisterUser)
                    } else {
                        "注册失败，请再次尝试".show()
                    }
                }
        }
        val cloudCodeName = "AddUser"
        val params = JSONObject()

        params.put("account", "11111")
        params.put("pwd", "111111")

        val cloudCode = AsyncCustomEndpoints()

        cloudCode.callEndpoint(
            cloudCodeName,
            params,
            object : CloudCodeListener() {
                override fun done(p0: Any?, p1: BmobException?) {
                    if (p1 == null) {
                        (p0 as? String)?.let{
                            Log.e("cx",it)
                        }
                    } else {
                        p1.message?.let { it1 -> Log.e("cx", "错误信息：$it1") }
                    }
                }

            })
    }

    private fun test() {
        val cloudCodeName = "ResetPwd"
        val params = JSONObject()

        params.put("objId", "088c267673")
        params.put("newPwd", "111111")

        val cloudCode = AsyncCustomEndpoints()

        cloudCode.callEndpoint(
            cloudCodeName,
            params,
            object : CloudCodeListener() {
                override fun done(p0: Any?, p1: BmobException?) {
                    if (p1 == null) {
                        (p0 as? String)?.let{
                            Log.e("cx",it)
                        }
                    } else {
                        p1.message?.let { it1 -> Log.e("cx", "错误信息：$it1") }
                    }
                }

            })
    }
}