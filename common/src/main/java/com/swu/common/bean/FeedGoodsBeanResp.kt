package com.swu.common.bean

import com.swu.common.model.GoodsCategoryModel

data class FeedGoodsBeanResp(
    val results: List<FeedGoodsBean>
) {
    fun convertToCategoryModelList(): List<GoodsCategoryModel> {
        val list = mutableListOf<GoodsCategoryModel>()
        var random = 0
        val randomList = mutableListOf<Int>()
        for (i in 1..12) {
            random = results.indices.random()
            while (randomList.contains(random)) {
                random = results.indices.random()
            }
            list.add(results[random].convertToModel())
            randomList.add(random)
        }
        return list
    }
}

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
) {
    fun convertToModel() = GoodsCategoryModel(
        numId.toString(), picUrl, title, sales, promotionPrice, goodsName
    )

    override fun toString(): String {
        return "FeedGoodsBean(createdAt='$createdAt', detailUrl='$detailUrl', goodsName='$goodsName', numId=$numId, objectId='$objectId', originPrice=$originPrice, picUrl='$picUrl', promotionPrice=$promotionPrice, sales=$sales, sellerId='$sellerId', shopName='$shopName', title='$title', updatedAt='$updatedAt')"
    }

}