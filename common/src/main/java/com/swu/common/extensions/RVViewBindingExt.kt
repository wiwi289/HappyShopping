package com.swu.common.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified T: ViewBinding> Class<T>.inflate(parent: ViewGroup): T {
    val method = this.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return method.invoke(this, LayoutInflater.from(parent.context), parent, false) as T
}
