package com.swu.common.extensions

import java.lang.StringBuilder

fun String.getUrl(): String {
    return "http:$this"
}