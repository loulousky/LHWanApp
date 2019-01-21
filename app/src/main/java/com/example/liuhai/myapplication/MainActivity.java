package com.example.liuhai.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;

import base.BaseFunActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import router.RouterPath;

@Route(path = RouterPath.APP_MODULE_MAIN)
public class MainActivity extends BaseFunActivity {

    private PublishSubject publishSubject = PublishSubject.create();//统一的事件分发


    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        Disposable d = publishSubject.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                //做状态栏或者底部
            }
        });
        disposable.add(d);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    protected void setListener() {

    }
}
