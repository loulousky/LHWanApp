package com.example.liuhai.homemodule.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：liuhai
 * 时间：2019/1/16:17:57
 * 邮箱：185587041@qq.com
 * 说明：
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        list = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if (i < list.size()) {
            fragment = list.get(i);

        } else {
            fragment = list.get(0);

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
