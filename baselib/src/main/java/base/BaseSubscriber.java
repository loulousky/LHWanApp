package base;

import error.AppException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：liuhai
 * 时间：2019/1/10:12:00
 * 邮箱：185587041@qq.com
 * 说明：
 */
public abstract  class BaseSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onPreparerepare(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onError(new AppException(e));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onPreparerepare(Disposable d);

    public abstract void onError(AppException throwable);

    public abstract void onSuccess(T t);
}
