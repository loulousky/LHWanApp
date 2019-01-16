package com.example.liuhai.myapplication.weclome.view;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.myapplication.R;
import com.example.liuhai.myapplication.weclome.WeclomeCallContact;
import com.liuhai.permission.PermissionUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import base.BaseFunActivity;
import butterknife.BindView;
import retrofit2.http.Path;
import router.RouterPath;
import wiget.CircleImageVIew;

/**
 * 作者：liuhai
 * 时间：2019/1/10:9:27
 * 邮箱：185587041@qq.com
 * 说明：
 */
@Route(path = RouterPath.APP_MODULE_WECLOMECALL)
public class WeclomeCallActivity<WeclomePresent> extends BaseFunActivity<com.example.liuhai.myapplication.weclome.present.WeclomePresent> implements WeclomeCallContact.WeclomeCallView {

    @BindView(R.id.img_accept_call)
    CircleImageVIew imgAcceptCall;
    @BindView(R.id.img_decline_call)
    CircleImageVIew imgDeclineCall;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_weclome_call;
    }
    @Override
    protected void init() {
        //注入P层
        presenter = new com.example.liuhai.myapplication.weclome.present.WeclomePresent(this);
    }
    @Override
    protected void setListener() {
        imgAcceptCall.setColorRes(R.color.callaccept);
        imgDeclineCall.setColorRes(R.color.calldenil);
        //设置上下动画
        imgAcceptCall.start();
        imgAcceptCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //可能会做一些数据预加载或者统计 或者权限申请
                in();
                startActivity(RouterPath.APP_MODULE_MAINPAGE);
            }
        });
        imgDeclineCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //可能会做些数据管理或者统计
                out();


                finish();
            }
        });
    }
    @Override
    public void in() {
        presenter.in();
        PermissionUtils.getInstance().requestPermission(this, new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                //go to mianpage

                Toast.makeText(getApplicationContext(),"测试点击",0).show();

            }
        }, new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {

            }
        }, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE);
    }
    @Override
    public void out() {
        presenter.out();
    }

    //改背景
    @Override
    public void changeBackGround() {

    }
}
