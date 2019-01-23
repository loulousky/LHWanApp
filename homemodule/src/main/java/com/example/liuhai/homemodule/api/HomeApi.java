package com.example.liuhai.homemodule.api;


import com.example.liuhai.homemodule.bean.BaseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：liuhai
 * 时间：2019/1/22:9:37
 * 邮箱：185587041@qq.com
 * 说明：
 */
public interface HomeApi {

    //获得视频的链接https://gank.io/api/today


    @GET("search/query/listview/category/Android/count/10/page/{page}")
    public Observable<BaseData> getListHot(@Path("page") String page);




}
