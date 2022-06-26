package com.swu.base

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.viewbinding.ViewBinding
import com.swu.base.status_bar.StatusBarUtil
import com.swu.lib_base.R

/**
 * Created by chenxiong
 * date 11/26/21
 */
abstract class BaseActivity: AppCompatActivity() {

    protected abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        initListener()
    }

    abstract fun initData()

    abstract fun initListener()

    //跳转并可设置传参
    inline fun <reified T : BaseActivity> startActivityAndFinish(parseParamsCall: Intent.() -> Unit = {}) {
        Intent(this, T::class.java).let { intent ->
            intent.parseParamsCall()
            startActivity(intent)
        }
        finish()
    }

    protected fun openFragment(fragment: Fragment) {
        FragmentUtil.getInstance().startFragment(this, fragment, R.id.container)
    }

    protected fun immerseStatusBar() {
        StatusBarUtil.setTranslucentStatus(this)
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this,0x55000000)
        }
    }
}