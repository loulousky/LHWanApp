package com.example.liuhai.homemodule.main.present;

import android.util.Log;

import com.example.liuhai.homemodule.bean.BaseData;
import com.example.liuhai.homemodule.bean.VidaoData;
import com.example.liuhai.homemodule.main.HomeContact;
import com.example.liuhai.homemodule.main.model.HomeModel;

import java.util.List;

import base.BasePresentImpl;
import base.BasePresenter;
import base.BaseView;
import base.OnSubscribeSuccess;
import error.AppException;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 作者：liuhai
 * 时间：2019/1/22:10:44
 * 邮箱：185587041@qq.com
 * 说明：
 */
public class HomePresent extends BasePresenter implements HomeContact.present {
    HomeModel model = new HomeModel();

    public HomePresent(BaseView activity) {
        super(activity);
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDettachView() {

    }


    @Override
    public void loadDisCover(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess) {

    }

    @Override
    public void loadHot(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess) {

        try {


            model.loadHot(page).flatMap(new Function<BaseData, ObservableSource<List<VidaoData>>>() {
                @Override
                public ObservableSource<List<VidaoData>> apply(BaseData baseData) throws Exception {
                    return Observable.just(baseData.results);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriberNoProgress(subscribeSuccess));
        } catch (Exception e) {
            new AppException(e).printStackTrace();
            subscribeSuccess.onError(new AppException(e));
            // e.printStackTrace();
        }
    }

    @Override
    public void loadNew(int page, OnSubscribeSuccess<List<VidaoData>> subscribeSuccess) {

    }

    @Override
    public void loadClassify() {

    }

}
