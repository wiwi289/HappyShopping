package com.swu.common.network.request

import java.lang.reflect.Type

class GetRequest<T>(private val dataType: Type) : BaseRequest<T>(dataType) {

    override val method: String = "GET"

}