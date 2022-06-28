package com.swu.feed.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class LeftMenuBaseHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: DATA)

    abstract fun getVB(): ViewBinding
}