package com.swu.feed.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.swu.base.recyclerview_v2.BaseVH
import com.swu.common.extensions.inflate
import com.swu.feed.databinding.LeftFirstMenuItemLayoutBinding
import com.swu.feed.databinding.LeftMenuItemLayoutBinding
import com.swu.feed.model.LeftMenuModel

class LeftMenuAdapter: RecyclerView.Adapter<LeftMenuBaseHolder<LeftMenuModel>>() {

    private val unSelectedColor = Color.parseColor("#FFFFFF")
    private val selectedColor = Color.parseColor("#EE8332")

    private val allCategory = arrayOf(
        LeftMenuModel("衣服",true),
        LeftMenuModel("电脑",false),
        LeftMenuModel("水杯",false),
        LeftMenuModel("小吃",false),
        LeftMenuModel("手表",false),
        LeftMenuModel("裤子",false),
        LeftMenuModel("男鞋",false),
        LeftMenuModel("护肤品",false),
        LeftMenuModel("书籍",false),
        LeftMenuModel("背包",false)
    )

    private var curSelectPos = 0

    private var onItemClickCallBack: ((Int) -> Unit)? = null

    class LeftMenuVH(private val binding: LeftMenuItemLayoutBinding): LeftMenuBaseHolder<LeftMenuModel>(binding.root) {
         override fun bind(data: LeftMenuModel) {
            binding.menuTitle.text = data.title
        }

        override fun getVB() = binding
    }

    class LeftHeaderMenuVH(private val binding: LeftFirstMenuItemLayoutBinding): LeftMenuBaseHolder<LeftMenuModel>(binding.root) {
        override fun bind(data: LeftMenuModel) {
            binding.menuTitle.text = data.title
        }

        override fun getVB() = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == Header)
        LeftHeaderMenuVH(LeftFirstMenuItemLayoutBinding::class.java.inflate(parent))
    else
        LeftMenuVH(LeftMenuItemLayoutBinding::class.java.inflate(parent))

    override fun onBindViewHolder(holder: LeftMenuBaseHolder<LeftMenuModel>, position: Int) {
            holder.bind(allCategory[position])
        if (position == 0) {
            setBgColor((holder.getVB() as LeftFirstMenuItemLayoutBinding).root, position)
        } else {
            setBgColor((holder.getVB() as LeftMenuItemLayoutBinding).root, position)
        }

        holder.getVB().root.setOnClickListener {
            reFreshSelectStatus(position)
            onItemClickCallBack?.invoke(position)
        }

    }

    private fun setBgColor(view: View, position: Int) {
        view.setBackgroundColor(
            if (allCategory[position].selected) selectedColor else unSelectedColor
        )
    }

    override fun getItemCount(): Int = allCategory.size

    fun reFreshSelectStatus(position: Int) {
        if (curSelectPos == position) return
        allCategory[curSelectPos].selected = false
        allCategory[position].selected = true
        notifyItemChanged(curSelectPos)
        notifyItemChanged(position)
        curSelectPos = position
    }

    override fun getItemViewType(position: Int): Int = if (position == 0)
        Header
    else
        NormalItem

    fun setOnItemClickCallBack(onItemClickCallBack: ((Int) -> Unit)) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun getTitle(pos: Int) = allCategory[pos].title

    fun getCurSelectedIndex() = curSelectPos

    fun getTitleIndex(rightMenuGroupName: String): Int {
        for (i in allCategory.indices) {
            if (allCategory[i].title == rightMenuGroupName) return i
        }
        return 0
    }
    companion object {
        private const val Header = 100
        private const val NormalItem = 101
    }
}