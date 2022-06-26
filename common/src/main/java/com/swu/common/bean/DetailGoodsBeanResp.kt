package com.swu.common.bean

data class DetailGoodsBeanResp(
    val results: List<DetailGoodsBean>
) {
    fun getDetailGoodsBean() = if (results.isNullOrEmpty())
        null
    else
        results[0]
}

data class DetailGoodsBean(
    val bigPic: String,
    val createdAt: String,
    val deliveryScore: String,
    val detailImgList: List<String>,
    val itemScore: String,
    val location: String,
    val numId: String,
    val objectId: String,
    val originalPrice: String,
    val price: String,
    val propImgList: List<Any>,
    val scoreP: String,
    val shopName: String,
    val skus: List<Sku>,
    val stocks: String,
    val title: String,
    val totalSold: Int,
    val updatedAt: String
)

data class Sku(
    val price: String,
    val quantity: String
)