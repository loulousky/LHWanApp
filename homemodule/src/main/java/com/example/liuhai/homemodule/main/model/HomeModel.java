package com.example.liuhai.homemodule.main.model;

import com.example.liuhai.homemodule.api.HomeApi;
import com.example.liuhai.homemodule.bean.BaseData;
import com.example.liuhai.homemodule.main.HomeContact;

import io.reactivex.Observable;
import network.HttpClient;

/**
 * 作者：liuhai
 * 时间：2019/1/22:9:36
 * 邮箱：185587041@qq.com
 * 说明：
 */
public class HomeModel implements HomeContact.model {
    //探索的数据
    @Override
    public Observable<BaseData> loadDisCover(int pageNo) throws Exception {
        return HttpClient.getInstace().create(HomeApi.class).getListHot(pageNo+"");
    }

    @Override
    public  Observable<BaseData> loadHot(int pageNo) throws Exception {
           return HttpClient.getInstace().create(HomeApi.class).getListHot(pageNo+"");
    }



    @Override
    public  Observable<BaseData> loadNew(int pageNo) throws Exception {
        return HttpClient.getInstace().create(HomeApi.class).getListHot(pageNo+"");
    }

    @Override
    public  Observable<BaseData> loadClassify() throws Exception {
        return null;
    }



}
