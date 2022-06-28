package com.swu.feed.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swu.base.FragmentController
import com.swu.base.show
import com.swu.base.view_model.BaseViewModel
import com.swu.common.Api
import com.swu.common.bean.DetailGoodsBeanResp
import com.swu.common.bean.FeedGoodsBeanResp
import com.swu.common.bean.FeedHotGoodsBean
import com.swu.common.bean.FeedHotGoodsBeanResp
import com.swu.common.constants.BombMethodConstants
import com.swu.common.model.GoodsCategoryModel
import com.swu.common.network.ApiParam
import com.swu.common.network.BombRequest
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FeedViewModel(context: Context,
                    fragmentController: FragmentController
) : BaseViewModel(context, fragmentController) {

    private val allCategoryName = arrayOf(
        "男装",
        "电脑",
        "水杯",
        "网红小吃",
        "男士手表",
        "牛仔裤",
        "潮流男鞋",
        "护肤品",
        "程序设计书籍",
        "背包"
    )

    private val deferredList = mutableListOf<Deferred<FeedGoodsBeanResp>>()

    var leftMenuSelectedId = 0

    private var _categoryLiveData = MutableLiveData<List<GoodsCategoryModel>>()

    val categoryLiveData: MutableLiveData<List<GoodsCategoryModel>>
        get() = _categoryLiveData

    private var _hotFeedData = MutableLiveData<List<FeedHotGoodsBean>>()

    val hotFeedData: MutableLiveData<List<FeedHotGoodsBean>>
        get() = _hotFeedData

    fun fetchAllCategoryData() {
        viewModelScope.launch {
            for (goodsName in allCategoryName) {
                deferredList.add(async {
                    getACategoryData(goodsName)
                })
            }
            convertToModel()
        }
    }

    private suspend fun getACategoryData(goodsName: String) =
        BombRequest.Get<FeedGoodsBeanResp>()
            .setMethodName(BombMethodConstants.GetFeedGoodsByName)
            .setApiParam(ApiParam("name", goodsName))
            .await() ?: FeedGoodsBeanResp(listOf())

    private suspend fun convertToModel() {
        val list = mutableListOf<GoodsCategoryModel>()
        deferredList.forEach { deferred ->
            list.addAll(deferred.await().convertToCategoryModelList())
        }
        _categoryLiveData.value = list
    }

    fun fetchFeedPageHotData() {
        viewModelScope.launch {
            val feedHotGoodsBeanResp = BombRequest.Get<FeedHotGoodsBeanResp>()
                .setMethodName(BombMethodConstants.GetAllFeedHotGoods)
                .await()
            if (feedHotGoodsBeanResp == null || feedHotGoodsBeanResp.results.isEmpty()) {
                "加载失败，请退出重试".show()
                return@launch
            }
            _hotFeedData.value = feedHotGoodsBeanResp.results
        }
    }
}