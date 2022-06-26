package com.swu.base

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentController(private val fragmentA: FragmentActivity, @IdRes private val containerId: Int) {

    fun startFragment(fragment: Fragment, @AnimatorRes @AnimRes inAnim: Int? = null) {
        val fragmentM: FragmentManager = fragmentA.supportFragmentManager
        val transaction: FragmentTransaction = fragmentM.beginTransaction()
        fragmentM.findFragmentByTag(fragment.javaClass.canonicalName)?.let { targetFragment ->
            if (targetFragment.isAdded) {
                if (targetFragment.isHidden) transaction.show(targetFragment)
                return
            }
        }
        transaction.add(containerId, fragment, fragment.javaClass.canonicalName)
        inAnim?.let { transaction.setCustomAnimations(it, 0) }
        transaction.addToBackStack(fragment.javaClass.canonicalName).commit()
    }

    fun switchFragment(fromFragment: Fragment, toFragment: Fragment, @AnimatorRes @AnimRes inAnim: Int? = null) {
        val fragmentM: FragmentManager = fragmentA.supportFragmentManager
        val transaction: FragmentTransaction = fragmentM.beginTransaction()
        transaction.hide(fromFragment)
        if (!toFragment.isAdded) {
            transaction.add(containerId, toFragment, toFragment.javaClass.canonicalName)
            inAnim?.let { transaction.setCustomAnimations(it, 0) }
        } else if (!toFragment.isVisible) {
            transaction.show(toFragment)
        }
        transaction.addToBackStack(fromFragment.javaClass.canonicalName).commit()
    }

    fun replaceFragment(toFragment: Fragment, @AnimatorRes @AnimRes inAnim: Int? = null) {
        val fragmentM: FragmentManager = fragmentA.supportFragmentManager
        val transaction: FragmentTransaction = fragmentM.beginTransaction()
        fragmentM.popBackStack()
        inAnim?.let { transaction.setCustomAnimations(it, 0) }
        transaction.replace(containerId, toFragment, toFragment.javaClass.canonicalName)
                .addToBackStack(toFragment.javaClass.canonicalName)
                .commit()
    }

    fun closeFragment(fragment: Fragment, @AnimatorRes @AnimRes outAnim: Int? = null) {
        val fragmentM: FragmentManager = fragmentA.supportFragmentManager
        val transaction: FragmentTransaction = fragmentM.beginTransaction()
        val targetFragment = fragmentM.findFragmentByTag(fragment.javaClass.canonicalName)
        if (targetFragment != null && targetFragment.isAdded) {
            outAnim?.let { transaction.setCustomAnimations(0, it) }
            transaction.remove(fragment).commit()
        }
    }

}