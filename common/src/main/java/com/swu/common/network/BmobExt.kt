package com.swu.common.network

import android.util.Log
import cn.bmob.v3.AsyncCustomEndpoints
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.CloudCodeListener
import com.google.gson.Gson
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T> AsyncCustomEndpoints.await(methodName: String, apiParam: ApiParam, type: Type): T? =
    suspendCoroutine { continuation ->
        callEndpoint(methodName, apiParam.getJsonParamObj(), object : CloudCodeListener(){
            override fun done(p0: Any?, p1: BmobException?) {
                if (p1 == null) {
                    (p0 as? String)?.let {
                        continuation.resume(Gson().fromJson(it, type))
                    }
                } else {
                    Log.e("cx", p1.message ?: "未知错误信息")
                    continuation.resume(null)
                }
            }
        })
    }

suspend fun AsyncCustomEndpoints.await(methodName: String, apiParam: ApiParam, failedCallBack: (Boolean) -> Unit ): Any? =
    suspendCoroutine {
        callEndpoint(methodName, apiParam.getJsonParamObj(), object : CloudCodeListener(){
            override fun done(p0: Any?, p1: BmobException?) {
                failedCallBack.invoke(p1 == null)
            }
        })
    }