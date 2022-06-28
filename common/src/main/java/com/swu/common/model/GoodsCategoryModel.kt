package com.swu.common.model

data class GoodsCategoryModel(
    val numId: String,
    val imgUrl: String,
    val title: String,
    val sales: Int,
    val price: Float,
    val goodsName: String
) {
    fun getGroupName() = when(goodsName) {
        "背包" -> "背包"
        "程序设计书籍" -> "书籍"
        "男装" -> "衣服"
        "电脑" -> "电脑"
        "水杯" -> "水杯"
        "潮流男鞋" -> "男鞋"
        "网红小吃" -> "小吃"
        "牛仔裤" -> "裤子"
        "护肤品" -> "护肤品"
        else -> "手表"
    }

    override fun toString(): String {
        return "GoodsCategoryModel(numId='$numId', imgUrl='$imgUrl', title='$title', sales=$sales, price=$price, goodsName='$goodsName')"
    }

    companion object {
        fun getDefault() = GoodsCategoryModel(
            "657511666801",
            "//img.alicdn.com/imgextra/i3/2206595486617/O1CN01avDASF1vIkVCjEeUT_!!0-item_pic.jpg",
            "高档形蜡烛灯 实薰 n馨座 浪漫温底 台灯 香氛 香木 融蜡灯 小奢",
            10,
            45f,
            "台灯"
        )
    }


}
