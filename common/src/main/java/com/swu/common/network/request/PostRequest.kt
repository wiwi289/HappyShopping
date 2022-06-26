package com.swu.common.network.request

import java.lang.reflect.Type

class PostRequest<T>(private val dataType: Type): BaseRequest<T>(dataType) {

    override val method: String = "POST"

}