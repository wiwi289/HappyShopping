package com.swu.base.recyclerview

interface ICustomView<D: IBaseCustomViewModel> {
    fun setData(data: D)
}
