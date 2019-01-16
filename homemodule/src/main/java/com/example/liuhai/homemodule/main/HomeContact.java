package com.example.liuhai.homemodule.main;


import base.BaseModuel;
import base.BaseView;
import io.reactivex.Observable;

/**
 * 作者：liuhai
 * 时间：2019/1/16:17:04
 * 邮箱：185587041@qq.com
 * 说明：统一封装的接口
 */
public interface HomeContact {

    public interface model extends BaseModuel {

        public <T> Observable<T> loadDisCover(int pageNo);

        public <T> Observable<T> loadHot(int pageNo);

        public <T> Observable<T> loadNew(int pageNo);

        public <T> Observable<T> loadClassify();


    }


    public interface view extends BaseView {
        public void loadData();

        public void setEmptyView();

        public void loadmore();

        public void refush();

    }


    public interface present{

        public void loadData();

        public void loadmore();

        public void setEmptyView();

        public void refush();

    }



}
