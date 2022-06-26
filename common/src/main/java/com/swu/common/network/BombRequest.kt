package com.swu.common.network

import com.swu.common.network.request.GetRequest
import com.swu.common.network.request.PostRequest

object BombRequest {

    inline fun <reified T> Get(): GetRequest<T> = GetRequest(T::class.java)

    inline fun <reified T> Post(): PostRequest<T> = PostRequest(T::class.java)

}