package com.example.liuhai.myapplication.main.present;

import com.example.liuhai.myapplication.main.MainContact;

import base.BasePresenter;
import base.BaseView;
import base.ILoadingView;

/**
 * 作者：liuhai
 * 时间：2019/1/16:10:53
 * 邮箱：185587041@qq.com
 * 说明：
 */
public class MainPagePresent extends BasePresenter implements MainContact.present {
    public MainPagePresent(BaseView activity) {
        super(activity);
    }

    public MainPagePresent(ILoadingView view, BaseView activity) {
        super(view, activity);
    }

    @Override
    public void onAttachView() {



    }

    @Override
    public void onDettachView() {

    }

    @Override
    public void checkUpdate() {

    }
}
