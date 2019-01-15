package base;


import android.view.View;

import error.AppException;

/**
 * 作者：liuhai
 * 时间：2019/1/10:14:01
 * 邮箱：185587041@qq.com
 * 说明：常规集成的Activity
 */
public abstract class BaseFunActivity<T extends BasePresenter> extends BaseActivity implements ILoadingView {

    //P层接口
    public T presenter;


    @Override
    public void showLoading() {
        showLoadingDialog("加载中..");
    }

    @Override
    public void showError(AppException errorThrowable) {
        errorThrowable.printStackTrace();
        quickToast(errorThrowable.getMessage());
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        if(presenter!=null) {
            getLifecycle().addObserver(presenter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除生命周期的监听
        if(presenter!=null) {
            getLifecycle().removeObserver(presenter);
        }
    }

    /**
     *添加Fragment
     *
     */



    /**
     * 删除Fragmet
     */


}
