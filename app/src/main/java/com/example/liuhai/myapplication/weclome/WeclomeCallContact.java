package com.example.liuhai.myapplication.weclome;

import com.example.liuhai.myapplication.weclome.view.WeclomeCallActivity;

import base.BaseModuel;
import base.BaseView;
import base.ILoadingView;

/**
 * 作者：liuhai
 * 时间：2019/1/15:14:28
 * 邮箱：185587041@qq.com
 * 说明：{@link WeclomeCallActivity}的接口相关集成
 */
public interface WeclomeCallContact {

    public interface WeclomeCallView extends BaseView {

        //进入主页
        public void in();


        //退出应用
        public void out();


    }

    //暂时WeclomeCallActivity用不到
    public interface WeclomeCallModel extends BaseModuel{

    }

    /*
       P层
     */
    public interface  WeclomeCallPresent{
        public void in();

        public void out();

    }



}
