package com.swu.order.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swu.base.FragmentController
import com.swu.base.show
import com.swu.base.view_model.BaseViewModel
import com.swu.common.bean.DetailGoodsBean
import com.swu.common.bean.DetailGoodsBeanResp
import com.swu.common.constants.BombMethodConstants
import com.swu.common.network.ApiParam
import com.swu.common.network.BombRequest
import kotlinx.coroutines.launch

class OrderViewModel(context: Context,
                     fragmentController: FragmentController
) : BaseViewModel(context, fragmentController) {

    private var _detailGoodsInfo = MutableLiveData<DetailGoodsBean>()

    val detailGoodsInfo: MutableLiveData<DetailGoodsBean>
        get() = _detailGoodsInfo

    fun fetchGoodsDetailInfo(numId: String) {
        viewModelScope.launch {
            val detailGoodsBeanResp = BombRequest.Get<DetailGoodsBeanResp>()
                .setMethodName(BombMethodConstants.GetDetailGoodsInfo)
                .setApiParam(ApiParam("numId", numId))
                .await()
            if (detailGoodsBeanResp == null || detailGoodsBeanResp.results.isEmpty()) {
                "加载失败，请退出重试".show()
                return@launch
            }

            _detailGoodsInfo.value = detailGoodsBeanResp.getDetailGoodsBean()
        }
    }
}