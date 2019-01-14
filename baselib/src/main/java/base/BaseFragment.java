package base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import wiget.ProgressDialog;

/**
 * 作者：liuhai
 * 时间：2019/1/10:9:27
 * 邮箱：185587041@qq.com
 * 说明：基础的Fragment,强调宿主Acitity必须是BaseActivity类型的
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;
    public BaseActivity activity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(this.getActivity()).inflate(getLayoutId(), null);
        ButterKnife.bind(this, rootView);//绑定butternife
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if ( getArguments() != null) {
            initParam(getArguments());
        }
        //路由注入
        ARouter.getInstance().inject(this);
        init();
        setListener();
        return rootView;
    }
    /**
     * 基本Toast
     * @param toast
     */
    public void quickToast(String toast) {
        activity.quickToast(toast);
    }


    public void showLoadingDialog(String text) {
        activity.showLoadingDialog(text);
    }

    public void showLoadingDialog(String loadText, boolean cancelable) {
      activity.showLoadingDialog(loadText,cancelable);
    }

    public void closeLoadingDialog() {
      activity.closeLoadingDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        closeLoadingDialog();
    }

    /**
     * 做一些获取BUNDLE的数据
     * @param savedInstanceState
     */
    public abstract  void initParam(Bundle savedInstanceState);




    /**
     * 返回ContentViewID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 做初始化操作
     */
    protected abstract void init();

    /**
     * 设置监听
     */
    protected abstract void setListener();


}
