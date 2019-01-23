package com.example.liuhai.homemodule.main;


import android.provider.MediaStore;

import com.example.liuhai.homemodule.bean.VidaoData;

import java.util.List;

import base.BaseModuel;
import base.BaseView;
import base.OnSubscribeSuccess;
import error.AppException;
import io.reactivex.Observable;

/**
 * 作者：liuhai
 * 时间：2019/1/16:17:04
 * 邮箱：185587041@qq.com
 * 说明：统一封装的接口
 */
public interface HomeContact {

    public interface model extends BaseModuel {

        public <T> Observable<T> loadDisCover(int pageNo) throws Exception;

        public <T> Observable<T> loadHot(int pageNo) throws Exception;

        public <T> Observable<T> loadNew(int pageNo) throws Exception;

        public <T> Observable<T> loadClassify() throws Exception;


    }


    public interface view extends BaseView {

        /**
         * flag 判断请求哪个接口 一个fragment写全部
         *
         * @param refush 是刷新还是下载加载
         */
        public void loadData(boolean refush);

        public void setEmptyView();

        public void loadmore();

        public void refush();

    }


    public interface present {

        public void loadDisCover(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess);

        public void loadHot(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess);

        public void loadNew(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess);

        public void loadClassify();


    }


}
