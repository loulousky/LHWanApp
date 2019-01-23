package com.example.liuhai.homemodule.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liuhai.homemodule.R;
import com.example.liuhai.homemodule.bean.VidaoData;

import java.util.List;

/**
 * 作者：liuhai
 * 时间：2019/1/22:12:09
 * 邮箱：185587041@qq.com
 * 说明：Video的adapter
 */
public class HomeListAdapter extends BaseQuickAdapter<VidaoData, BaseViewHolder> {
    public HomeListAdapter(int layoutResId, @Nullable List<VidaoData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VidaoData item) {
          helper.setText(R.id.video_title,item.desc);

    }
}
