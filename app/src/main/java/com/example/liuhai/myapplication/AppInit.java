package com.example.liuhai.myapplication;

import android.content.Context;
import android.support.multidex.MultiDex;

import base.BaseInitInterface;

/**
 * 作者：liuhai
 * 时间：2019/1/16:12:06
 * 邮箱：185587041@qq.com
 * 说明：首页的APPLICATI初始化
 */
public class AppInit implements BaseInitInterface {

    @Override
    public void init(Context context) {
        MultiDex.install(context);
    }

    @Override
    public void trim() {

    }
}
