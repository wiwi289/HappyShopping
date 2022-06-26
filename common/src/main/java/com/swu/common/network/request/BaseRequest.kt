package com.swu.common.network.request

import cn.bmob.v3.AsyncCustomEndpoints
import com.swu.common.network.ApiParam
import com.swu.common.network.await
import java.lang.reflect.Type

abstract class BaseRequest<T>(private val dataType: Type) {

    protected abstract val method: String

    protected var requestName: String? = null

    private var apiParam: ApiParam? = null

    fun setApiParam(apiParam: ApiParam): BaseRequest<T> {
        this.apiParam = apiParam
        return this
    }

    fun setMethodName(requestName: String): BaseRequest<T> {
        this.requestName = requestName
        return this
    }

    suspend fun await(): T? {
        if (requestName.isNullOrEmpty() || apiParam == null) {
            return null
        }
        return AsyncCustomEndpoints().await<T>(requestName!!, apiParam!!, dataType)
    }

    // 不需返回值的请求
    suspend fun emit(failedCallBack: (Boolean) -> Unit) {
        if (requestName.isNullOrEmpty() || apiParam == null) {
            failedCallBack.invoke(false)
            return
        }
        AsyncCustomEndpoints().await(requestName!!, apiParam!!, failedCallBack)
    }

}