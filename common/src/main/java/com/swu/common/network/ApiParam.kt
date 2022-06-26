package com.swu.common.network

import org.json.JSONObject

class ApiParam {

    private val params = mutableMapOf<String, String>()

    constructor() {}

    constructor(paramKey: String, paramValue: String) {
        params[paramKey] = paramValue
    }

    constructor(paramKey: String, paramValue: Int) {
        params[paramKey] = paramValue.toString()
    }

    fun append(paramKey: String, paramValue: String): ApiParam {
        params[paramKey] = paramValue
        return this
    }

    fun append(paramKey: String, paramValue: Int): ApiParam {
        params[paramKey] = paramValue.toString()
        return this
    }

    fun getJsonParamObj(): JSONObject {
        val jsonObj = JSONObject()
        for (entry in params.entries) {
            jsonObj.put(entry.key, entry.value)
        }
        return jsonObj
    }
}