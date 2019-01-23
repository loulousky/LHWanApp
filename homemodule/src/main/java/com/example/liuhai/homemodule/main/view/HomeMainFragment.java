package com.example.liuhai.homemodule.main.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.homemodule.R;
import com.example.liuhai.homemodule.R2;
import com.example.liuhai.homemodule.main.adapter.HomeViewPagerAdapter;
import com.example.liuhai.homemodule.main.present.HomePresent;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import base.BaseFunFragment;
import base.BaseView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;
import router.RouterPath;

/**
 * 作者：liuhai
 * 时间：2019/1/16:16:59
 * 邮箱：185587041@qq.com
 * 说明：主页面的Fragment
 */
@Route(path = RouterPath.HOME_MOUDLE_MAIN)
public class HomeMainFragment extends BaseFunFragment<HomePresent> implements BaseView {
    @BindView(R2.id.home_tablayout)
    TabLayout homeTablayout;
    @BindView(R2.id.home_viewpager)
    ViewPager homeViewpager;
    PublishSubject publishSubject;

    public void setPublishSubject(PublishSubject publishSubject) {
        this.publishSubject = publishSubject;
    }

    @Override
    public void initParam(Bundle savedInstanceState) {
        //
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homemain;
    }

    @Override
    protected void init() {
        super.init();
        present = new HomePresent(this);
    }

    @Override
    protected void setListener() {
        for (int i = 0; i < 3; i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.hometab_item, homeTablayout, false);
            TextView tabicon = itemView.findViewById(R.id.tabIcon);
            switch (i) {
                case 0:
                    tabicon.setText("探索");
                    break;
                case 1:
                    tabicon.setText("热门");
                    break;
                case 2:
                    tabicon.setText("最新");
                    break;
                case 3:
                    tabicon.setText("分类");
                default:
                    break;
            }
            homeTablayout.addTab(homeTablayout.newTab().setCustomView(itemView));
            List<Fragment> list = new ArrayList<>();
            HomeListFragment fragment = (HomeListFragment) ARouter.getInstance().build(RouterPath.HOME_MOUDLE_HOT).withString("flag", "hot").navigation();
            HomeListFragment fragment2 = (HomeListFragment) ARouter.getInstance().build(RouterPath.HOME_MOUDLE_HOT).withString("flag", "hot").navigation();
            HomeListFragment fragment3 = (HomeListFragment) ARouter.getInstance().build(RouterPath.HOME_MOUDLE_HOT).withString("flag", "hot").navigation();
            HomeListFragment fragment4 = (HomeListFragment) ARouter.getInstance().build(RouterPath.HOME_MOUDLE_HOT).withString("flag", "hot").navigation();
            fragment.setPresent(present);
            fragment2.setPresent(present);
            fragment3.setPresent(present);
            fragment4.setPresent(present);
            list.add(fragment2);
            list.add(fragment);
            list.add(fragment3);
            list.add(fragment4);
            List<String> title = new ArrayList<>();
            title.add("探索");
            title.add("热门");
            title.add("最新");
            title.add("分类");
            HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager(), list, title);
            homeViewpager.setAdapter(adapter);
            homeViewpager.setCurrentItem(1);
            homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                    //要在这里做动画处理去掉底部和上面部分的颜色 需要通知Activity
                    if (i == 0) {
                        publishSubject.onNext(v);
                    }
                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
            homeTablayout.setupWithViewPager(homeViewpager);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
