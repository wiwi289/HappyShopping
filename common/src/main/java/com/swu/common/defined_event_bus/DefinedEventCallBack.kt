package com.swu.common.defined_event_bus

/**
 * Created by chenxiong
 * date 2/12/22
 */
interface DefinedEventCallBack {
    fun onEventChange(event: String, res: Any ? = null)
}