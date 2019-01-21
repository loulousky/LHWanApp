package com.example.liuhai.myapplication.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.homemodule.main.view.HomeMainFragment;
import com.example.liuhai.myapplication.R;
import com.example.liuhai.myapplication.main.MainContact;
import com.example.liuhai.myapplication.main.present.MainPagePresent;
import com.example.liuhai.myapplication.weiget.drawable.TabDrawable;
import com.gyf.barlibrary.ImmersionBar;

import base.BaseFunActivity;
import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import jp.wasabeef.blurry.Blurry;
import router.RouterPath;

@Route(path = RouterPath.APP_MODULE_MAINPAGE)
public class MainPage extends BaseFunActivity<MainPagePresent> implements MainContact.view {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.mainpage_tablayout)
    TabLayout mainpageTablayout;

    private int selectedTab;
    private PublishSubject<Float> subject = PublishSubject.create();//做底部Tab的渐变改动
    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_page;
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void init() {
       ImmersionBar.setStatusBarView(this,findViewById(R.id.barview));
        Disposable disposable = subject.subscribe(new Consumer<Float>() {
            @Override
            public void accept(Float integer) throws Exception {
                //接收透明度
                mainpageTablayout.setAlpha(integer);
                if (integer == 0) {
                    mainpageTablayout.setVisibility(View.GONE);
                } else {
                    mainpageTablayout.setVisibility(View.VISIBLE);
                }


            }
        });
        compositeDisposable.add(disposable);


    }

    @Override
    protected void setListener() {
        setTab();
        mainpageTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "0":
                        ShowFragment(fragment1);
                        HideFragment(fragment2);
                        Toast.makeText(getApplicationContext(), "one", 0).show();

                        break;

                    case "2":
                        ShowFragment(fragment2);
                        HideFragment(fragment1);
                        Toast.makeText(getApplicationContext(), "two", 0).show();
                        break;
                    case "1":
                        break;

                    default:
                        break;


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
            mainpageTablayout.addTab(mainpageTablayout.newTab().setCustomView(itemView).setText(i + ""));
        }
        fragment1 = new BlankFragment();
        fragment2 = (HomeMainFragment) ARouter.getInstance().build(RouterPath.HOME_MOUDLE_MAIN).navigation();
        ((HomeMainFragment) fragment2).setPublishSubject(subject);
        addFragment(R.id.fragment_content, fragment1, "tab1", false);
        addFragment(R.id.fragment_content, fragment2, "tab2", false);
        ShowFragment(fragment1);
        HideFragment(fragment2);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void showUpdate() {

    }

    @Override
    public void changeBottomAlpha() {

    }
}
