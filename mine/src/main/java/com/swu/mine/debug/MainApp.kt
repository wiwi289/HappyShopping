package com.swu.mine.debug

import cn.bmob.v3.Bmob
import com.swu.base.BaseApplication

class MainApp: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this, "b061581fc443707bd25add77202aa34b")
    }
}