package base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuhai.permission.PermissionUtils;

import error.AppException;

/**
 * 作者：liuhai
 * 时间：2019/1/11:10:36
 * 邮箱：185587041@qq.com
 * 说明：继承此Fragment
 */
public abstract  class BaseFunFragment<T extends BasePresentImpl> extends BaseFragment implements ILoadingView{

    //present外部set进来 //本来想用dagger2的，奈何本地网络差集成不下来FUCK~
    public  T present;
    public PermissionUtils permissionUtils;
    public SharedPreferences sharedPreferences;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(present!=null) {
            getLifecycle().removeObserver(present);
        }

    }

    @Override
    public void showLoading() {
       activity.showLoadingDialog("加载中...");
    }

    @Override
    public void showError(AppException errorThrowable) {
       activity.quickToast(errorThrowable.getMessage());
    }

    @Override
    public void hideLoading() {
       activity.closeLoadingDialog();
    }

    @Override
    protected void init() {
       present= (T) ViewModelProviders.of(this).get(present.getClass());
        if(present!=null) {
            getLifecycle().addObserver(present);
        }
        permissionUtils = activity.permissionUtils;
        sharedPreferences = activity.sharedPreferences;



    }
}
