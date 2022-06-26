package com.swu.common.defined_event_bus

import java.util.*

/**
 * Created by chenxiong
 * date 2/12/22
 */
object DefinedEventController {
    private val callbackMap = HashMap<String, DefinedEventCallBack>()

    fun registerCallBack(vararg eventKey: String, callBack: DefinedEventCallBack) {

        eventKey.forEach { key ->
            if (callbackMap.containsKey(key)) {
                throw RuntimeException("this callback key:$eventKey has been registered, please check...")
            }
            callbackMap[key] = callBack
        }
    }

    fun dispatchEvent(eventKey: String, res: Any? = null) {
        val callback = callbackMap[eventKey]
            ?: return
        callback.onEventChange(eventKey, res)
    }

    fun unregisterCallBack(callBack: DefinedEventCallBack) {
        val iterator = callbackMap.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (callBack == callbackMap[entry.key]) {
                iterator.remove()
            }
        }
    }

}