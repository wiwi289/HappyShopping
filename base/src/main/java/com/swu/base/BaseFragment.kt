package com.swu.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.swu.lib_base.R

/**
 * Created by chenxiong
 * date 11/26/21
 */
abstract class BaseFragment(): Fragment() {

    protected abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //将Fragment大小设置为屏幕大小减去底部导航栏大小
        setRealSize()
        init()
        initListener()
    }

    protected open fun setRealSize(){

    }

    protected fun jumpToFragment(fragment: Fragment, @AnimatorRes @AnimRes inAnim: Int) {
//        jumpListener?.jumpToFragment(fragment, inAnim)
        FragmentUtil.getInstance().startFragment(requireActivity(), fragment, R.id.container, inAnim)
    }

//    @JvmName("setJumpFragmentCallBack1")
//    fun setJumpFragmentCallBack(jumpListener: FragmentJumpListener) {
//        this.jumpListener = jumpListener
//    }

    abstract fun init()

    protected open fun initData() {}

    abstract fun initListener()

//    interface FragmentJumpListener {
//        fun jumpToFragment(fragment: Fragment, @AnimatorRes @AnimRes anim: Int)
//    }

}