package com.example.liuhai.myapplication.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.liuhai.myapplication.R;
import com.example.liuhai.myapplication.main.MainContact;
import com.example.liuhai.myapplication.main.present.MainPagePresent;
import com.example.liuhai.myapplication.weiget.drawable.TabDrawable;

import base.BaseFunActivity;
import butterknife.BindView;
import jp.wasabeef.blurry.Blurry;
import router.RouterPath;

@Route(path = RouterPath.APP_MODULE_MAINPAGE)
public class MainPage extends BaseFunActivity<MainPagePresent> implements MainContact.view {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.mainpage_tablayout)
    TabLayout mainpageTablayout;
    @BindView(R.id.blurview)
    ImageView blurview;
    private int selectedTab;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_page;
    }

    @Override
    protected void init() {
        presenter = new MainPagePresent(this);
    }

    @Override
    protected void setListener() {
        setTab();
    }

    private void setTab() {
        for (int i = 0; i < 3; i++) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.tab_item, mainpageTablayout, false);
            ImageView tabicon = itemView.findViewById(R.id.tabIcon);
            switch (i) {
                case 0:
                    tabicon.setImageResource(R.drawable.select_tab_one);
                    break;
                case 1:
                    tabicon.setImageResource(R.drawable.home_ico_diy);
                    break;
                case 2:
                    tabicon.setImageResource(R.drawable.select_tab_two);
                    break;
                default:
                    break;
            }
            mainpageTablayout.addTab(mainpageTablayout.newTab().setCustomView(itemView));
        }
        TabDrawable drawable = new TabDrawable();
        drawable.setAlpha(220);
        blurview.setImageDrawable(drawable);

    }


    @Override
    public void showUpdate() {

    }

    @Override
    public void changeBottomAlpha() {

    }
}
