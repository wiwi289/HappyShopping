package com.swu.order.activity

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.swu.base.BaseActivity
import com.swu.base.FragmentController
import com.swu.base.UIUtils
import com.swu.base.viewBinding
import com.swu.base.view_model.BaseViewModelFactory
import com.swu.common.extensions.getUrl
import com.swu.common.extensions.loadPic
import com.swu.order.databinding.ActivityGoodsDetailBinding
import com.swu.order.view_model.OrderViewModel
import com.swu.order.widget.GoodsDetailBottomBarLayout

class GoodsDetailActivity : BaseActivity() {

    override val binding: ActivityGoodsDetailBinding by viewBinding(ActivityGoodsDetailBinding::inflate)

    private val fragmentController: FragmentController by lazy {
        FragmentController(this, 0)
    }

    private val orderViewModel: OrderViewModel by lazy {
        ViewModelProvider(this, BaseViewModelFactory(this, fragmentController)).get(OrderViewModel::class.java)
    }

    private val detailBottomBarLayout: GoodsDetailBottomBarLayout by lazy {
        GoodsDetailBottomBarLayout(this)
    }

    override fun initData() {
        orderViewModel.fetchGoodsDetailInfo("529781869151")
        detailBottomBarLayout.attach(this)
    }

    override fun initListener() {
        orderViewModel.detailGoodsInfo.observe(this) { detailGoodsBean ->
            with(binding) {
                headImg.loadPic(detailGoodsBean.bigPic.getUrl())
                with(detailHeader) {
                    setOriginalPrice(detailGoodsBean.originalPrice)
                    setPromotionPrice(detailGoodsBean.price)
                    setTitle(detailGoodsBean.title)
                }
                detailParams.setAddress(detailGoodsBean.location)
                with(detailShopDes) {
                    setDispatchDes(detailGoodsBean.deliveryScore)
                    setSellerDes(detailGoodsBean.scoreP)
                    setGoodsDes(detailGoodsBean.itemScore)
                    setShopName(detailGoodsBean.shopName)
                }
            }
            with(binding.detailImgContainer) {
                detailGoodsBean.detailImgList.forEach { picUrl ->
                    ImageView(this.context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            UIUtils.dp2px(this.context, 500))
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        loadPic(picUrl.getUrl())
                    }.also { addView(it) }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detailBottomBarLayout.detach()
    }
}