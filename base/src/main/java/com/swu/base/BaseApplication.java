package com.swu.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {

    private static Application sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Application getContext() {
        return sContext;
    }

}
