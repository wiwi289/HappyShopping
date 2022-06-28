package com.swu.feed.widget

import android.content.Context
import android.util.AttributeSet
import com.swu.base.BaseView
import com.swu.base.viewBinding
import com.swu.feed.R
import com.swu.feed.databinding.CategoryItemLayoutBinding

class CategoryItem @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : BaseView(context, attr, style) {

    companion object {
        private var onItemClickCallBack: ((CItemModel) -> Unit)? = null

        fun setOnItemClickCallBack(onItemClickCallBack: ((CItemModel) -> Unit)) {
            this.onItemClickCallBack = onItemClickCallBack
        }
    }

    override val binding: CategoryItemLayoutBinding = viewBinding(CategoryItemLayoutBinding::inflate, true)

    init {
        val typeArr = context.obtainStyledAttributes(attr, R.styleable.CategoryItem)
        binding.itemImg.setImageResource(typeArr.getResourceId(R.styleable.CategoryItem_item_pic, R.drawable.clothes))
        binding.itemTitle.text = typeArr.getString(R.styleable.CategoryItem_item_title)
        typeArr.recycle()

        setOnClickListener {
            onItemClickCallBack?.invoke(getQueryModel(it.id))
        }
    }

    override fun adjust() {

    }

    override fun getMBinding() = binding

    private fun getQueryModel(id: Int) = when(id) {
        R.id.categoryBag -> CItemModel("背包",9)
        R.id.categoryBook -> CItemModel("程序设计书籍",8)
        R.id.categoryClothes -> CItemModel("男装",0)
        R.id.categoryComputer -> CItemModel("电脑",1)
        R.id.categoryCup -> CItemModel("水杯",2)
        R.id.categoryShoe -> CItemModel("潮流男鞋",6)
        R.id.categorySnack -> CItemModel("网红小吃",3)
        R.id.categoryTrousers -> CItemModel("牛仔裤",5)
        R.id.categorySkinCare -> CItemModel("护肤品",7)
        else -> CItemModel("男士手表",4)
    }

    data class CItemModel(val title: String, val index: Int) {
        override fun toString(): String {
            return "CItemModel(title='$title', index=$index)"
        }
    }

}