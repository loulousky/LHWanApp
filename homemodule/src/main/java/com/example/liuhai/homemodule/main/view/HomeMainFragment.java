package com.example.liuhai.homemodule.main.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuhai.homemodule.R;
import com.example.liuhai.homemodule.R2;
import com.example.liuhai.homemodule.main.adapter.HomeViewPagerAdapter;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：liuhai
 * 时间：2019/1/16:16:59
 * 邮箱：185587041@qq.com
 * 说明：主页面的Fragment
 */
public class HomeMainFragment extends BaseFragment {


    @BindView(R2.id.home_tablayout)
    TabLayout homeTablayout;
    @BindView(R2.id.home_viewpager)
    ViewPager homeViewpager;

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
        //全局的状态栏初始化
        ImmersionBar.with(this).barColor(R.color.main_color).init();


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
            list.add(new Fragment());
            list.add(new Fragment());
            list.add(new Fragment());
            list.add(new Fragment());
            HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager(), list);
            homeViewpager.setAdapter(adapter);
            homeViewpager.setCurrentItem(1);
            homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                    //要在这里做动画处理去掉底部和上面部分的颜色 需要通知Activity
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
