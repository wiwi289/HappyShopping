package com.swu.common.bean

import com.swu.common.model.GoodsCategoryModel

data class FeedHotGoodsBeanResp(
    val results: List<FeedHotGoodsBean>
) {
    fun getFeedHotGoodsBean() = if (results.isNullOrEmpty())
        null
    else
        results[0]
}

data class FeedHotGoodsBean(
    val createdAt: String,
    val detailUrl: String,
    val goodsName: String,
    val numId: Long,
    val objectId: String,
    val originPrice: Float,
    val picUrl: String,
    val promotionPrice: Float,
    val sales: Int,
    val sellerId: String,
    val shopName: String,
    val title: String,
    val updatedAt: String
) {
    fun convertToModel() = GoodsCategoryModel(
        numId.toString(), picUrl, title, sales, promotionPrice.toFloat(), goodsName
    )
}