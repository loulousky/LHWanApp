package com.example.liuhai.homemodule.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.liuhai.homemodule.R;
import com.example.liuhai.homemodule.R2;
import com.example.liuhai.homemodule.bean.VidaoData;
import com.example.liuhai.homemodule.main.HomeContact;
import com.example.liuhai.homemodule.main.adapter.HomeListAdapter;
import com.example.liuhai.homemodule.main.present.HomePresent;

import java.util.ArrayList;
import java.util.List;

import base.BaseFunFragment;
import base.OnSubscribeSuccess;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import error.AppException;
import router.RouterPath;
import wiget.GridSpacingItemDecoration;

@Route(path = RouterPath.HOME_MOUDLE_HOT)
public class HomeListFragment extends BaseFunFragment<HomePresent> implements HomeContact.view {

    @Autowired
    String flag;
    @BindView(R2.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R2.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private List<VidaoData> dataList = new ArrayList<>();
    private int page = 1;
    private HomeListAdapter adapter;

    public HomeListFragment() {
        // Required empty public constructor
    }

    public void setPresent(HomePresent present1) {
        present = present1;
    }

    public static HomeListFragment newInstance(String param1) {
        HomeListFragment fragment = new HomeListFragment();
        Bundle args = new Bundle();
        args.putString("flag", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initParam(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void setListener() {
        adapter = new HomeListAdapter(R.layout.homelist_item, dataList);
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 15, true));
        adapter.bindToRecyclerView(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setPreLoadNumber(5);
        loadData(true);
        loadmore();
        refush();


    }


    @Override
    public void loadData(final boolean refush) {
        if (flag == "hot") {
            present.loadHot(page, new OnSubscribeSuccess<List<VidaoData>>() {
                @Override
                public void onSuccess(List<VidaoData> vidaoData) {
                    if (refush) {
                        adapter.setNewData(vidaoData);
                        adapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                    } else {
                        if (vidaoData == null) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.addData(vidaoData);
                            adapter.loadMoreComplete();
                        }
                        adapter.setUpFetchEnable(true);
                    }


                }

                @Override
                public void onError(AppException error) {
                    adapter.setEnableLoadMore(true);
                    swipeLayout.setRefreshing(false);
                }
            });

        }

    }

    @Override
    public void setEmptyView() {
        TextView emptyview = new TextView(getActivity());
        emptyview.setText("暂时无法获取数据");
        adapter.setEmptyView(emptyview);
    }

    @Override
    public void loadmore() {


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                page++;
                adapter.setUpFetchEnable(false);
                loadData(false);


            }
        }, recyclerview);

    }

    @Override
    public void refush() {

        adapter.setEnableLoadMore(false);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;

                loadData(true);
            }
        });


    }


}
