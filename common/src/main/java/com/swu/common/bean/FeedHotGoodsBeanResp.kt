package com.swu.common.bean

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
    val originPrice: Int,
    val picUrl: String,
    val promotionPrice: Int,
    val sales: Int,
    val sellerId: String,
    val shopName: String,
    val title: String,
    val updatedAt: String
)