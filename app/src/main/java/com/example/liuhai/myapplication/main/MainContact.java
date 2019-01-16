package com.example.liuhai.myapplication.main;


import base.BaseModuel;
import base.BaseView;
import io.reactivex.Observer;

/**
 * 作者：liuhai
 * 时间：2019/1/16:10:37
 * 邮箱：185587041@qq.com
 * 说明：主页集成接口
 */
public interface MainContact {

    /**
     *
     * @param <T> 更新实体BEAN
     */
    public interface model<T> extends BaseModuel {

        public Observer<T> checkUpdate();//检查更新

    }

    public interface present{

        public void checkUpdate();

    }


    public interface view extends BaseView {


        public void showUpdate();//检查更新


        public void changeBottomAlpha();//改变底部导航透明度


    }






}
