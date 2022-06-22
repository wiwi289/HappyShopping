package com.swu.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * @ClassName BaseApplication
 * @Author zhangjun
 * @Date 2021/11/28 14:48
 * @Description BaseApplication封装
 */
public class BaseApplication extends Application {

    private static Application sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Bmob.initialize(this, "b061581fc443707bd25add77202aa34b");
    }

    public static Application getContext() {
        return sContext;
    }

}
