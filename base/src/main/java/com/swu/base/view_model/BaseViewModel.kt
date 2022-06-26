package com.swu.base.view_model

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.swu.base.FragmentController

abstract class BaseViewModel(
        @SuppressLint("StaticFieldLeak") protected val context: Context,
        protected val fragmentController: FragmentController
        ) : ViewModel() {

        fun getMContext() = context

        fun getFrController() = fragmentController

        fun startFragment(fragment: Fragment, @AnimatorRes @AnimRes inAnim: Int? = null) = fragmentController.startFragment(fragment, inAnim)

        fun switchFragment(fromFragment: Fragment, toFragment: Fragment,  @AnimatorRes @AnimRes inAnim: Int? = null) =
                fragmentController.switchFragment(fromFragment, toFragment, inAnim)

        fun replaceFragment(toFragment: Fragment,  @AnimatorRes @AnimRes inAnim: Int? = null) =
                fragmentController.replaceFragment(toFragment, inAnim)

        fun closeFragment(fragment: Fragment, @AnimatorRes @AnimRes inAnim: Int? = null) = fragmentController.closeFragment(fragment, inAnim)
}