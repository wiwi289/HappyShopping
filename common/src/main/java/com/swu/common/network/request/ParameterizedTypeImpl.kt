package com.swu.common.network.request

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ParameterizedTypeImpl(private val raw: Class<*>, private val args: Array<Type>) : ParameterizedType {

    override fun getRawType(): Type {
        return raw
    }

    override fun getOwnerType(): Type? = null

    override fun getActualTypeArguments(): Array<Type> = args
}
