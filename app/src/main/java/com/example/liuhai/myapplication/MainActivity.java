package com.example.liuhai.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import base.BaseFunActivity;
import router.RouterPath;

@Route(path = RouterPath.APP_MODULE_MAIN)
public class MainActivity extends BaseFunActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }
}
