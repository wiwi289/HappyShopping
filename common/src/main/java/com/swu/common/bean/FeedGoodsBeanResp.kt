package com.swu.common.bean

data class FeedGoodsBeanResp(
    val results: List<FeedGoodsBean>
)

data class FeedGoodsBean(
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
)