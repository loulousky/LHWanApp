package com.example.liuhai.myapplication;

import android.app.Application;

import base.ModuleInitManager;

/**
 * 作者：liuhai
 * 时间：2019/1/11:11:39
 * 邮箱：185587041@qq.com
 * 说明：
 */
public class MainApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ModuleInitManager.getInstance().initModules(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        /**
         * 肯定不会调用这个回调
         */
        ModuleInitManager.getInstance().Trim();
    }
}
