package com.swu.base.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swu.base.FragmentController
import java.lang.RuntimeException

class BaseViewModelFactory(private val context: Context,
                           private val fragmentController: FragmentController)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            modelClass.declaredConstructors.first().newInstance(context, fragmentController) as T
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}