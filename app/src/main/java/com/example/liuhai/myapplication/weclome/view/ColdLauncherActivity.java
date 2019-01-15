package com.example.liuhai.myapplication.weclome.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.myapplication.R;

/**
 * 冷启动Activity 用来冷启动，启动第一次进去引导Activity或期后的主页面
 * 关联冷启动的theme预防白屏或者黑屏
 */
public class ColdLauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_launcher);
        ARouter.getInstance().inject(this);//注入框架
    }



}
