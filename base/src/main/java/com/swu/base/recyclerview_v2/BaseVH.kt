package com.swu.base.recyclerview_v2

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseVH<VIEW: ViewBinding, DATA> (protected val binding: VIEW) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: DATA)

    fun getVB() = binding
}