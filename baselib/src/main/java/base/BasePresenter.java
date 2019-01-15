package base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;


import error.AppException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * <请描述这个类是干什么的>
 *
 * @author liuhai
 * @data: 2016/6/27 12:54
 * @version: V1.0
 * 集成VIEWMODEL管理整个P层的生命周期
 */
public abstract class BasePresenter<T extends BaseView> implements BasePresent {
    protected ILoadingView mView;
    protected T mActivity;
    protected CompositeDisposable mCompositeSubscription;


    public BasePresenter(BaseView activity) {
        this.mView = (ILoadingView) activity;
        this.mActivity = (T) activity;
    }

    public BasePresenter(ILoadingView view, BaseView activity) {
        mView = view;
        mActivity = (T) activity;
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

    /**
     * 因为在请求过程中用户可能会进行其他操作销毁当前页面，
     * 这边必须要通过addSubscribe 来做请求，防止页面销毁后订阅者
     * 的生命周期没结束抛异常
     *
     * @param subscription
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }

    protected <T> Observer<T> getSubscriber(final OnSubscribeSuccess<T> onSubscribeSuccess) {

        return  new BaseSubscriber<T>(){

            @Override
            public void onPreparerepare(Disposable d) {
                addSubscribe(d);
                mView.showLoading();
            }

            @Override
            public void onError(AppException throwable) {
                mView.hideLoading();
                mView.showError(throwable);
            }

            @Override
            public void onSuccess(T t) {
                mView.hideLoading();
                onSubscribeSuccess.onSuccess(t);
            }
        };


    }

    protected <T> Observer<T> getSubscriberNoProgress(final OnSubscribeSuccess<T> onSubscribeSuccess) {

        return  new BaseSubscriber<T>(){

            @Override
            public void onPreparerepare(Disposable d) {
                addSubscribe(d);
            }

            @Override
            public void onError(AppException throwable) {
                mView.showError(new AppException(throwable));
            }

            @Override
            public void onSuccess(T t) {
                onSubscribeSuccess.onSuccess(t);
            }
        };

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void detachView() {
        this.mView = null;
        mActivity = null;
        unSubscribe();
    }
}
