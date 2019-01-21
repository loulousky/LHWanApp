package base;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private FragmentManager fragmentManager;


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
        if (presenter != null) {
            getLifecycle().addObserver(presenter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除生命周期的监听
        if (presenter != null) {
            getLifecycle().removeObserver(presenter);
        }
    }

    /**
     * 添加Fragment
     */
    public synchronized void addFragment(int content, Fragment f, String tagname, boolean addstack) {
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tagname);
        if (fragment != null) {
            return ;
        } else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(content, f, tagname);
            if (addstack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }


    /**
     * 添加Fragment
     */
    public synchronized void ShowFragment(Fragment fragment) {
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    /**
     * 添加Fragment
     */
    public synchronized void HideFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(fragment).commit();
    }


    /**
     * 删除Fragmet
     */


}
