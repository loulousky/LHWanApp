package com.example.liuhai.myapplication.weclome.view;

import android.graphics.Color;
import android.media.MediaRouter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.myapplication.R;
import com.gyf.barlibrary.ImmersionBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import router.RouterPath;


/**
 * 冷启动Activity 用来冷启动，启动第一次进去引导Activity或期后的主页面
 * 关联冷启动的theme预防白屏或者黑屏
 */
@Route(path = RouterPath.APP_MODULE_MAIN)
public class ColdLauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_launcher);

        ImmersionBar.with(this).barColor(R.color.main_color).init();
        ARouter.getInstance().inject(this);//注入框架

        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                ARouter.getInstance().build(RouterPath.APP_MODULE_WECLOMECALL).navigation(ColdLauncherActivity.this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }


                });
            }
        });


    }


}
